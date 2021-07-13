package com.gs.lshly.biz.support.trade.service.bbc.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.TradeRightsGoodsTypeEnum;
import com.gs.lshly.biz.support.trade.enums.TradeRightsNewStateEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsSkuRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oy
 * @since 2020-12-06
 */
@Component
@Slf4j
public class BbcTradeRightsServiceImpl implements IBbcTradeRightsService {

    private final ITradeRightsRepository repository;
    private final ITradeRepository tradeRepository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    private final ITradeRightsGoodsRepository tradeRightsGoodsRepository;
    private final ITradeRightsImgRepository tradeRightsImgRepository;
    @Autowired
    private ITradeRightsLogRepository tradeRightsLogRepository;
    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    @DubboReference
    private IBbcShopRpc iBbcShopRpc;

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;

    @DubboReference
    private IBbcGoodsSkuRpc iBbcGoodsSkuRpc;

    public BbcTradeRightsServiceImpl(ITradeRightsRepository repository, ITradeRepository tradeRepository,
                                     ITradeGoodsRepository tradeGoodsRepository, ITradeRightsGoodsRepository tradeRightsGoodsRepository, ITradeRightsImgRepository tradeRightsImgRepository) {
        this.repository = repository;
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradeRightsGoodsRepository = tradeRightsGoodsRepository;
        this.tradeRightsImgRepository = tradeRightsImgRepository;
    }

    @Override
    public PageData<BbcTradeRightsVO.ListVO> pageData(BbcTradeRightsQTO.QTO qto) {
        QueryWrapper<TradeRights> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeRights> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcTradeRightsVO.ListVO.class, page);
    }

    @Override
    @Transactional
    public List<String> addTradeRights(BbcTradeRightsBuildDTO.ETO dto) {
/*        if (StringUtils.isEmpty(dto.getRightsRemark())) {
            throw new BusinessException("请填写售后原因");
        }*/
        if (!ObjectUtil.isAllNotEmpty(dto, dto.getProductData(), dto.getRightsType(), dto.getRightsReasonType(),
                dto.getRefundAmount(), dto.getRefundPoint(), dto.getTradeId())) {
            throw new BusinessException("参数不全或为空");
        }
        //根据订单id查询订单数据
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("订单不存在");
        }
        List<String> tradeRightsIdList = new ArrayList<>();
        TradeRights tradeRights;
        if (!dto.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())) {
            BbcTradeRightsVO.GoodsTotalVO goodsTotalVO;
            //获取当前全部退款商品的金额与积分
            List<String> skuIdList = CollUtil.getFieldValues(dto.getProductData(), "skuId", String.class);
            BbcTradeRightsBuildDTO.GoodsTotalDTO goodsTotalDTO = new BbcTradeRightsBuildDTO.GoodsTotalDTO();
            goodsTotalDTO.setTradeId(dto.getTradeId());
            goodsTotalDTO.setSkuIds(skuIdList);
            goodsTotalVO = goodsTotal(goodsTotalDTO);
            //保存售后商品与图片凭证信息
            for (BbcTradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()) {

                BigDecimal refundAmount = BigDecimal.ZERO;
                BigDecimal refundPoint = BigDecimal.ZERO;
                QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
                wrapper.eq("trade_id", trade.getId());
                wrapper.eq("sku_id", productData.getSkuId());
                TradeGoods tradeGoods = tradeGoodsRepository.getOne(wrapper);

                //保存售后信息
                tradeRights = new TradeRights();
                BeanUtil.copyProperties(trade, tradeRights);
                tradeRights.setId(null);
                tradeRights.setTradeId(trade.getId());
                tradeRights.setUserId(trade.getUserId());
                BbcUserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(trade.getUserId());
                if (ObjectUtil.isNotEmpty(innerUserInfoVO)) {
                    tradeRights.setPhone(innerUserInfoVO.getPhone());
                }
                tradeRights.setRightsType(dto.getRightsType());
                tradeRights.setRightsReasonType(dto.getRightsReasonType());
                tradeRights.setOrderCode(trade.getTradeCode());
                tradeRights.setShouldRefundAmount(dto.getRefundAmount());
                tradeRights.setShouldRefundPoint(dto.getRefundPoint());
                tradeRights.setState(TradeRightsEndStateEnum.待处理.getCode());
                tradeRights.setRightsRemark(dto.getRightsRemark());
                tradeRights.setReturnType(TradeRightsReturnTypeEnum.自行寄回.getCode());
                tradeRights.setApplyTime(LocalDateTime.now());
                tradeRights.setRefundMoneyType(TradeRightsRefundMoneyTypeEnum.售后申请退款.getCode());
                tradeRights.setRefundType(TradeRightsRefundTypeEnum.原路退回.getCode());
                tradeRights.setRecvFullAddres(dto.getRecvFullAddres());
                tradeRights.setIsHide(0);
                if (ObjectUtil.isNotEmpty(tradeGoods.getTradeAmount()) && tradeGoods.getTradeAmount().compareTo(BigDecimal.ZERO) > 0) {
                    refundAmount = refundAmount.add(tradeGoods.getTradeAmount().multiply(dto.getRefundAmount().divide(goodsTotalVO.getAmount(), BigDecimal.ROUND_HALF_UP)));
                    tradeRights.setShouldRefundAmount(refundAmount);
                }
                if (ObjectUtil.isNotEmpty(tradeGoods.getTradePointAmount()) && tradeGoods.getTradePointAmount().compareTo(BigDecimal.ZERO) > 0) {
                    refundPoint = refundPoint.add(tradeGoods.getTradePointAmount().multiply(dto.getRefundPoint().divide(goodsTotalVO.getPoint(), BigDecimal.ROUND_HALF_UP)));
                    tradeRights.setShouldRefundPoint(refundPoint);
                }
                repository.save(tradeRights);
                tradeRightsIdList.add(tradeRights.getId());

                //保存售后商品信息
                TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
                BeanUtil.copyProperties(tradeGoods, tradeRightsGoods);
                tradeRightsGoods.setTradeGoodsId(tradeGoods.getId());
                tradeRightsGoods.setOrderCode(trade.getTradeCode());
                if (productData.getQuantity() > tradeGoods.getQuantity()) {
                    throw new BusinessException("申请售后商品数量不能大于订单商品数量");
                }
                tradeRightsGoods.setQuantity(productData.getQuantity());
                tradeRightsGoods.setGoodsType(productData.getGoodsType());
                tradeRightsGoods.setRightsId(tradeRights.getId());
                tradeRightsGoods.setId(null);
                tradeRightsGoods.setIsRevocation(false);
                tradeRightsGoodsRepository.save(tradeRightsGoods);

                //保存售后图片信息
                List<BbcTradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
                if (ObjectUtils.isNotEmpty(rightsImgDataList)) {
                    List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
                    for (BbcTradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList) {
                        TradeRightsImg tradeRightsImg = new TradeRightsImg();
                        tradeRightsImg.setRightsId(tradeRights.getId());
                        tradeRightsImg.setTradeId(tradeRights.getTradeId());
                        tradeRightsImg.setRightsImg(rightsImgData.getRightsImg());
                        tradeRightsImgs.add(tradeRightsImg);
                    }
                    tradeRightsImgRepository.saveBatch(tradeRightsImgs);
                }
                //rightsGoodsAndImage(dto, tradeRightsGoodsS, rights);
                TradeRightsLog tradeRightsLog = new TradeRightsLog();
                tradeRightsLog.setRightsId(tradeRights.getId());
                tradeRightsLog.setState(TradeRightsEndStateEnum.待处理.getCode());
                tradeRightsLog.setContent("买家发起了退款申请，金额：" + tradeRights.getShouldRefundAmount().setScale(2) + "元，" + tradeRights.getShouldRefundPoint() + "积分。");
                tradeRightsLogRepository.save(tradeRightsLog);
            }
        } else {
            //换货
            //保存售后商品与图片凭证信息
            /*BigDecimal refundAmount = BigDecimal.ZERO;
            BigDecimal refundPoint = BigDecimal.ZERO;*/

            //如果申请过换货，不让申请
            String skuId = dto.getProductData().get(0).getSkuId();
            QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
            query.eq("trade_id", dto.getTradeId());
            query.eq("sku_id", skuId);
            int count = tradeRightsGoodsRepository.count(query);
            if (count != 0) {
                throw new BusinessException("您已申请过换货申请!");
            }

            //保存售后信息
            tradeRights = new TradeRights();
            BeanUtil.copyProperties(trade, tradeRights);
            tradeRights.setId(null);
            tradeRights.setTradeId(trade.getId());
            tradeRights.setUserId(trade.getUserId());
            BbcUserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(trade.getUserId());
            if (ObjectUtil.isNotEmpty(innerUserInfoVO)) {
                tradeRights.setPhone(innerUserInfoVO.getPhone());
            }
            tradeRights.setRightsType(dto.getRightsType());
            tradeRights.setRightsReasonType(dto.getRightsReasonType());
            tradeRights.setOrderCode(trade.getTradeCode());
            //tradeRights.setShouldRefundAmount(dto.getRefundAmount());
            //tradeRights.setShouldRefundPoint(dto.getRefundPoint());
            tradeRights.setState(TradeRightsEndStateEnum.待处理.getCode());
            tradeRights.setRightsRemark(dto.getRightsRemark());
            tradeRights.setReturnType(TradeRightsReturnTypeEnum.自行寄回.getCode());
            tradeRights.setApplyTime(LocalDateTime.now());
            //tradeRights.setRefundMoneyType(TradeRightsRefundMoneyTypeEnum.售后申请退款.getCode());
            //tradeRights.setRefundType(TradeRightsRefundTypeEnum.原路退回.getCode());
            tradeRights.setRecvFullAddres(dto.getRecvFullAddres());
            tradeRights.setIsHide(0);
/*            if (ObjectUtil.isNotEmpty(tradeGoods.getTradeAmount()) && tradeGoods.getTradeAmount().compareTo(BigDecimal.ZERO) > 0) {
                refundAmount = refundAmount.add(tradeGoods.getTradeAmount().multiply(dto.getRefundAmount().divide(goodsTotalVO.getAmount(), BigDecimal.ROUND_HALF_UP)));
                tradeRights.setShouldRefundAmount(refundAmount);
            }
            if (ObjectUtil.isNotEmpty(tradeGoods.getTradePointAmount()) && tradeGoods.getTradePointAmount().compareTo(BigDecimal.ZERO) > 0) {
                refundPoint = refundPoint.add(tradeGoods.getTradePointAmount().multiply(dto.getRefundPoint().divide(goodsTotalVO.getPoint(), BigDecimal.ROUND_HALF_UP)));
                tradeRights.setShouldRefundPoint(refundPoint);
            }*/
            repository.save(tradeRights);
            tradeRightsIdList.add(tradeRights.getId());
            for (BbcTradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()) {
                if (productData.getGoodsType().equals(TradeRightsGoodsTypeEnum.原商品.getCode())) {
                    QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
                    wrapper.eq("trade_id", trade.getId());
                    wrapper.eq("sku_id", productData.getSkuId());
                    TradeGoods tradeGoods = tradeGoodsRepository.getOne(wrapper);
                    //保存售后商品信息
                    TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
                    BeanUtil.copyProperties(tradeGoods, tradeRightsGoods);
                    tradeRightsGoods.setId(null);
                    tradeRightsGoods.setOrderCode(trade.getTradeCode());
                    if (ObjectUtil.isNotEmpty(tradeGoods)) {
                        if (productData.getQuantity() > tradeGoods.getQuantity()) {
                            throw new BusinessException("申请售后商品数量不能大于订单商品数量");
                        }
                    }
                    tradeRightsGoods.setQuantity(productData.getQuantity());
                    tradeRightsGoods.setGoodsType(productData.getGoodsType());

                    tradeRightsGoods.setTradeGoodsId(tradeGoods.getId());
                    tradeRightsGoods.setRightsId(tradeRights.getId());
                    tradeRightsGoods.setIsRevocation(false);
                    tradeRightsGoodsRepository.save(tradeRightsGoods);
                } else {
                    BbcUserShoppingCarVO.InnerSkuInfoVO skuInfo = iBbcGoodsSkuRpc.getSkuInfo(productData.getSkuId());
                    TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
                    tradeRightsGoods.setGoodsType(productData.getGoodsType());
                    tradeRightsGoods.setQuantity(productData.getQuantity());
                    tradeRightsGoods.setGoodsId(skuInfo.getGoodsId());
                    tradeRightsGoods.setGoodsName(skuInfo.getGoodsName());
                    tradeRightsGoods.setShopId(dto.getJwtShopId());
                    tradeRightsGoods.setMerchantId(dto.getJwtMerchantId());
                    tradeRightsGoods.setUserId(dto.getJwtUserId());
                    tradeRightsGoods.setSkuId(productData.getSkuId());
                    if (StrUtil.isNotEmpty(skuInfo.getSpecValue())) {
                        tradeRightsGoods.setSkuSpecValue(skuInfo.getSpecValue());
                    }
                    tradeRightsGoods.setIsRevocation(false);
                    tradeRightsGoods.setRightsId(tradeRights.getId());
                    tradeRightsGoodsRepository.save(tradeRightsGoods);
                }
            }
            //保存售后图片信息
            List<BbcTradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
            if (ObjectUtils.isNotEmpty(rightsImgDataList)) {
                List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
                for (BbcTradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList) {
                    TradeRightsImg tradeRightsImg = new TradeRightsImg();
                    tradeRightsImg.setRightsId(tradeRights.getId());
                    tradeRightsImg.setTradeId(tradeRights.getTradeId());
                    tradeRightsImg.setRightsImg(rightsImgData.getRightsImg());
                    tradeRightsImgs.add(tradeRightsImg);
                }
                tradeRightsImgRepository.saveBatch(tradeRightsImgs);
            }
            //rightsGoodsAndImage(dto, tradeRightsGoodsS, rights);

            TradeRightsLog tradeRightsLog = new TradeRightsLog();
            tradeRightsLog.setRightsId(tradeRights.getId());
            tradeRightsLog.setState(TradeRightsEndStateEnum.待处理.getCode());
            tradeRightsLog.setContent("买家发起了换货申请，待商家审核。");
            tradeRightsLogRepository.save(tradeRightsLog);
        }
        return tradeRightsIdList;
    }

/*    private TradeRights addTradeRights(BbcTradeRightsBuildDTO.ETO dto, Trade trade) {
        //根据售后类型新增售后记录
        TradeRights rights = new TradeRights();
*//*        if (ObjectUtil.isNotEmpty(tradeRights)) {
            rights.setIsTwoCheck(1);
            rights.setCheckState(TradeRightsNewStateEnum.待审核.getCode());
        }*//*
        rights.setTradeId(trade.getId());
        rights.setUserId(trade.getUserId());
        BbcUserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(trade.getUserId());
        if (ObjectUtil.isNotEmpty(innerUserInfoVO)) {
            rights.setPhone(innerUserInfoVO.getPhone());
        }
        rights.setShopId(trade.getShopId());
        rights.setMerchantId(trade.getMerchantId());
        rights.setOrderCode(trade.getTradeCode());
        rights.setShouldRefundAmount(dto.getRefundAmount());
        rights.setShouldRefundPoint(dto.getRefundPoint());
        rights.setState(TradeRightsEndStateEnum.提交申请.getCode());
        rights.setRightsType(dto.getRightsType());
        rights.setRightsReasonType(dto.getRightsReasonType());
        rights.setRightsRemark(dto.getRightsRemark());
        rights.setRefundRemarks(dto.getRightsRemark());
        //rights.setReturnType(dto.getReturnType());
        rights.setApplyTime(LocalDateTime.now());
        rights.setRefundMoneyType(TradeRightsRefundMoneyTypeEnum.售后申请退款.getCode());
        //rights.setRefundType(TradeRightsRefundTypeEnum.原路退回.getCode());
        rights.setIsHide(0);
        repository.save(rights);
        return rights;
    }

    *//**
     * 封装保存售后商品信息的集合
     *
     * @param dto
     * @param tradeCode
     * @param refundAmount
     * @param refundPoint
     * @return
     *//*
    private List<TradeRightsGoods> addTradeRightsGoodsList(BbcTradeRightsBuildDTO.ETO dto, String tradeCode, BigDecimal refundAmount, BigDecimal refundPoint) {
        List<TradeRightsGoods> tradeRightsGoodsS = new ArrayList<>();
        for (BbcTradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()) {
            TradeGoods tradeGoods = tradeGoodsRepository.getById(productData.getTradeGoodsId());
            if (ObjectUtils.isEmpty(tradeGoods)) {
                throw new BusinessException("订单商品数据不存在");
            }
            //根据订单id以及订单商品id查询已申请售后数据 判断是否可以申请售后(售后状态不等于驳回或已退货数量与购买数量一致则不允许申请售后)
            QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
            tradeRightsGoodsQueryWrapper.eq("trade_goods_id", productData.getTradeGoodsId());
            List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);
            if (CollUtil.isNotEmpty(tradeRightsGoodsList)) {
                for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
                    if (ObjectUtil.isNotEmpty(tradeRightsGoods) && tradeRightsGoods.getTradeId().equals(dto.getTradeId()) && tradeRightsGoods.getTradeGoodsId().equals(productData.getTradeGoodsId())) {
                        if (productData.getQuantity().intValue() > tradeGoods.getQuantity().intValue()) {
                            throw new BusinessException("不能退超过购买的数量");
                        }
                    }
                }
            }
*//*            for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
                if (ObjectUtils.isNotEmpty(tradeRightsGoods)) {
                    if (tradeRightsGoods.getTradeGoodsId().equals(productData.getTradeGoodsId())) {
                        throw new BusinessException("已经在执行售后操作");
                    }
                    if (ObjectUtils.isNotEmpty(tradeRightsGoods)) {
                        TradeRights tradeRights = repository.getById(tradeRightsGoods.getRightsId());
                        if (ObjectUtils.isNotEmpty(tradeRights)) {
                            if (productData.getQuantity().intValue() > tradeGoods.getQuantity().intValue()) {
                                throw new BusinessException("不能退超过购买的数量");
                            }
                        }
                    }
                }
            }*//*
            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal point = BigDecimal.ZERO;
            if (ObjectUtils.isNotEmpty(tradeGoods.getSalePrice())) {
                amount = tradeGoods.getSalePrice().multiply(new BigDecimal(productData.getQuantity()));
            }
            if (ObjectUtils.isNotEmpty(tradeGoods.getTradePointAmount())) {
                point = tradeGoods.getTradePointAmount().multiply(new BigDecimal(productData.getQuantity()));
            }
            refundAmount = refundAmount.add(amount);
            refundPoint = refundPoint.add(point);
            TradeRightsGoods rightsGoods = new TradeRightsGoods();
            rightsGoods.setTradeId(tradeGoods.getTradeId());
            rightsGoods.setTradeGoodsId(tradeGoods.getId());
            rightsGoods.setUserId(tradeGoods.getUserId());
            rightsGoods.setShopId(tradeGoods.getShopId());
            rightsGoods.setMerchantId(tradeGoods.getMerchantId());
            rightsGoods.setOrderCode(tradeCode);
            rightsGoods.setGoodsName(tradeGoods.getGoodsName());
            rightsGoods.setSkuId(tradeGoods.getSkuId());
            rightsGoods.setSkuSpecValue(tradeGoods.getSkuSpecValue());
            rightsGoods.setQuantity(productData.getQuantity());
            rightsGoods.setSalePrice(tradeGoods.getSalePrice());
            rightsGoods.setRefundAmount(amount);
            rightsGoods.setRefundPoint(refundPoint);
            rightsGoods.setGoodsType(productData.getGoodsType());
            tradeRightsGoodsS.add(rightsGoods);
        }
        return tradeRightsGoodsS;
    }

    */

    /**
     * 保存售后商品与图片凭证信息
     *
     * @param dto
     * @param
     * @param
     *//*
    private void rightsGoodsAndImage(BbcTradeRightsBuildDTO.ETO dto, List<TradeRightsGoods> tradeRightsGoodsS, TradeRights rights) {
        //保存售后商品表
        for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsS) {
            tradeRightsGoods.setRightsId(rights.getId());
            tradeRightsGoodsRepository.save(tradeRightsGoods);
        }

        //保存售后凭证
        List<BbcTradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
        if (ObjectUtils.isNotEmpty(rightsImgDataList)) {
            List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
            for (BbcTradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList) {
                TradeRightsImg tradeRightsImg = new TradeRightsImg();
                tradeRightsImg.setRightsId(rights.getId());
                tradeRightsImg.setTradeId(rights.getTradeId());
                tradeRightsImg.setRightsImg(rightsImgData.getRightsImg());
                tradeRightsImgs.add(tradeRightsImg);
            }
            tradeRightsImgRepository.saveBatch(tradeRightsImgs);
        }
    }*/
    @Override
    public BbcTradeRightsVO.DetailVO detailTradeRights(BbcTradeRightsDTO.IdDTO dto) {
        TradeRights tradeRights = repository.getById(dto.getId());
        BbcTradeRightsVO.DetailVO detailVo = new BbcTradeRightsVO.DetailVO();
        if (ObjectUtils.isEmpty(tradeRights)) {
            throw new BusinessException("没有数据");
        }
        BbcTradeRightsVO.ListVO listVO = fillTradeVO(tradeRights);
        QueryWrapper<TradeRightsImg> wrapper = MybatisPlusUtil.query();
        wrapper.eq("rights_id", tradeRights.getId());
        List<TradeRightsImg> tradeRightsImgList = tradeRightsImgRepository.list(wrapper);
        List<String> rightsImg = new ArrayList<>();
        if (CollUtil.isNotEmpty(tradeRightsImgList)) {
            rightsImg = CollUtil.getFieldValues(tradeRightsImgList, "rightsImg", String.class);
        }
        detailVo.setTradeRightImg(rightsImg);
        BeanUtil.copyProperties(listVO, detailVo);
        listVO.setStateDetails(TradeRightsEndStateEnum.getRemarkByCode(tradeRights.getState()));
       /* BeanUtils.copyProperties(tradeRights, detailVo);
        QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("rights_id", tradeRights.getId()));
        List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(query);
        if (CollUtil.isNotEmpty(tradeRightsGoodsList)) {
            List<BbcTradeRightsVO.TradeRightsGoodsVO> list = new ArrayList<>();
            for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
                BbcTradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbcTradeRightsVO.TradeRightsGoodsVO();
                BeanUtils.copyProperties(tradeRightsGoods, tradeRightsGoodsVO);
                BbcGoodsInfoVO.InnerServiceVO innerServiceVO = iBbcGoodsInfoRpc.innerSimpleServiceGoodsVO(tradeRightsGoods.getSkuId());
                if (ObjectUtils.isNotEmpty(innerServiceVO)) {
                    tradeRightsGoodsVO.setSkuImg(innerServiceVO.getGoodsImage());
                }
                list.add(tradeRightsGoodsVO);
            }
            detailVo.setTradeRightsGoodsVO(list);
        }
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(tradeRights.getShopId()));
        if (ObjectUtils.isNotEmpty(innerDetailVO)) {
            detailVo.setShopName(innerDetailVO.getShopName());
        }
        CommonShopVO.ShopServiceOutVO shopService = iCommonShopRpc.getShopService(tradeRights.getShopId());
*//*        if (ObjectUtils.isNotEmpty(shopService)) {
            detailVo.setQqNumber(shopService.getAccount());
            detailVo.setShopPhone(shopService.getPhone());
        }*//*
        QueryWrapper<TradeRightsImg> query1 = MybatisPlusUtil.query();
        query1.and(i -> i.eq("rights_id", tradeRights.getId()));
        List<TradeRightsImg> list = tradeRightsImgRepository.list(query1);
        if (ObjectUtils.isNotEmpty(list)) {
            detailVo.setTradeRightImg(ListUtil.getIdList(TradeRightsImg.class, list, "rightsImg"));
        }
        detailVo.setId(tradeRights.getId());*/
        return detailVo;

    }

    @Override
    public PageData<BbcTradeRightsVO.ListVO> tradeRightsListData(BbcTradeRightsQTO.RightsList qto) {
 /*       QueryWrapper<BbcTradeRightsQTO.RightsList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("`user_id`", qto.getJwtUserId()));
        wrapper.and(i -> i.ne("`is_hide`", 1));*/
/*        if(ObjectUtils.isNotEmpty(qto.getRightsType())){
            if(qto.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())){
                wrapper.and(i -> i.eq("rights_type",qto.getRightsType()));
            }else {
                List<Integer> rightsType = new ArrayList();
                    rightsType.add(TradeRightsTypeEnum.仅退款.getCode());
                    rightsType.add(TradeRightsTypeEnum.退货退款.getCode());
                wrapper.and(i -> i.in("rights_type",rightsType));
            }
        }*/
        //wrapper.orderByDesc("cdate");

        //IPage<BbcTradeRightsVO.ListVO> page = MybatisPlusUtil.pager(qto);
        //IPage<BbcTradeRightsVO.ListVO> voiPage = repository.selectTradeRightsList(page, wrapper);
        IPage<TradeRights> page = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("`user_id`", qto.getJwtUserId()));
        query.and(i -> i.ne("`is_hide`", 1));
        query.orderByDesc("cdate");
        repository.page(page, query);
        List<BbcTradeRightsVO.ListVO> voList = new ArrayList<>();
        if (CollUtil.isEmpty(page.getRecords())) {
            return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
        }
        for (TradeRights tradeRights : page.getRecords()) {
            //根据交易ID查询交易商品集合
            BbcTradeRightsVO.ListVO listVO = fillTradeVO(tradeRights);
            voList.add(listVO);
        }
        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public void returnGoods(BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto) {
        if (ObjectUtils.isEmpty(dto.getReturnGoodsLogisticsName())) {
            throw new BusinessException("请填写物流公司名字");
        }
        if (ObjectUtils.isEmpty(dto.getReturnGoodsLogisticsNum())) {
            throw new BusinessException("请填写物流单号");
        }
        TradeRights tradeRights = repository.getById(dto.getRightsId());
        if (ObjectUtils.isNull(tradeRights)) {
            throw new BusinessException("无售后单数据");
        }
        if (tradeRights.getState().equals(TradeRightsEndStateEnum.商家确认收货并退款.getCode())) {
            tradeRights.setReturnGoodsLogisticsName(dto.getReturnGoodsLogisticsName());
            tradeRights.setReturnGoodsLogisticsNum(dto.getReturnGoodsLogisticsNum());
            tradeRights.setReturnGoodsLogisticsDate(LocalDateTime.now());
            tradeRights.setState(TradeRightsEndStateEnum.商家同意.getCode());
            repository.saveOrUpdate(tradeRights);
        } else {
            throw new BusinessException("不允许操作");
        }

    }

    @Override
    public void confirmReceipt(BbcTradeRightsDTO.IdDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            TradeRights tradeRights = repository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(tradeRights)) {
                if (tradeRights.getState().equals(TradeRightsEndStateEnum.商家同意.getCode()) /*&& tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())*/) {
                    tradeRights.setState(TradeRightsEndStateEnum.换货完成.getCode());
                } else {
                    throw new BusinessException("不能修改状态");
                }
            } else {
                throw new BusinessException("查询不到售后单");
            }
            repository.updateById(tradeRights);
            Trade trade = tradeRepository.getById(tradeRights.getTradeId());
            if (ObjectUtil.isEmpty(trade)) {
                throw new BusinessException("查询不到订单");
            }
            trade.setTradeState(TradeStateEnum.已完成.getCode());
            TradeRightsLog tradeRightsLog = new TradeRightsLog();
            tradeRightsLog.setRightsId(tradeRights.getId());
            tradeRightsLog.setState(TradeRightsEndStateEnum.换货完成.getCode());
            tradeRightsLog.setContent("换货完成。");
            tradeRightsLogRepository.save(tradeRightsLog);
        } else {
            throw new BusinessException("请传售后表ID");
        }
    }

    @Override
    @Transactional
    public void revocationTradeRights(BbcTradeRightsBuildDTO.RevocationTradeRightsETO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtil.isEmpty(tradeRights)) {
            throw new BusinessException("查询不到售后单");
        }
        if (tradeRights.getState().equals(TradeRightsEndStateEnum.用户取消.getCode())) {
            throw new BusinessException("用户已取消");
        }
        //删除售后商品数据
        QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
        query.eq("rights_id", tradeRights.getId());
        query.eq("is_revocation", false);
        List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(query);
        for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
            tradeRightsGoods.setIsRevocation(true);
            tradeRightsGoodsRepository.updateById(tradeRightsGoods);
        }

        //删除售后表数据
        tradeRights.setState(TradeRightsEndStateEnum.用户取消.getCode());
        repository.updateById(tradeRights);

        //记录售后信息
        TradeRightsLog tradeRightsLog = new TradeRightsLog();
        tradeRightsLog.setRightsId(tradeRights.getId());
        tradeRightsLog.setState(TradeRightsEndStateEnum.用户取消.getCode());
        tradeRightsLog.setContent("用户撤销售后申请。");
        tradeRightsLogRepository.save(tradeRightsLog);
    }

    @Override
    @Transactional
    public void deleteRecord(BbcTradeRightsDTO.IdDTO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtil.isEmpty(tradeRights)) {
            throw new BusinessException("查询不到售后单");
        }
        tradeRights.setIsHide(1);
        repository.updateById(tradeRights);
    }

    @Override
    @Transactional
    public void updateTradeRights(BbcTradeRightsBuildDTO.UpdateETO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        //修改售后单
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtil.isEmpty(tradeRights)) {
            throw new BusinessException("查询不到售后单");
        }
        
        //判断状态对不对
        Integer state = tradeRights.getState();
        if(state.equals(20))
        	throw new BusinessException("商家已同意");
        BeanUtils.copyProperties(dto, tradeRights);
        repository.updateById(tradeRights);
        //修改售后商品
        QueryWrapper<TradeRightsGoods> goodsQuery = MybatisPlusUtil.query();
        goodsQuery.eq("rights_id", tradeRights.getId());
        tradeRightsGoodsRepository.remove(goodsQuery);
        QueryWrapper<TradeRightsImg> imageQuery = MybatisPlusUtil.query();
        imageQuery.eq("rights_id", tradeRights.getId());
        tradeRightsImgRepository.remove(imageQuery);
        //保存售后商品信息
        Trade trade = tradeRepository.getById(dto.getTradeId());
        for (BbcTradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()) {
            QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
            wrapper.eq("trade_id", trade.getId());
            wrapper.eq("sku_id", productData.getSkuId());
            TradeGoods tradeGoods = tradeGoodsRepository.getOne(wrapper);
            TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
            BeanUtil.copyProperties(tradeGoods, tradeRightsGoods);
            tradeRightsGoods.setId(null);
            if (productData.getGoodsType().equals(TradeRightsGoodsTypeEnum.原商品.getCode())) {
                tradeRightsGoods.setTradeGoodsId(tradeGoods.getId());
            }
            tradeRightsGoods.setOrderCode(trade.getTradeCode());
            if (productData.getQuantity() > tradeGoods.getQuantity()) {
                throw new BusinessException("申请售后商品数量不能大于订单商品数量");
            }
            tradeRightsGoods.setQuantity(productData.getQuantity());
            tradeRightsGoods.setGoodsType(productData.getGoodsType());
            tradeRightsGoods.setRightsId(tradeRights.getId());
            tradeRightsGoods.setIsRevocation(false);
            tradeRightsGoodsRepository.save(tradeRightsGoods);
        }
        //保存售后图片信息
        List<BbcTradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
        if (ObjectUtils.isNotEmpty(rightsImgDataList)) {
            List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
            for (BbcTradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList) {
                TradeRightsImg tradeRightsImg = new TradeRightsImg();
                tradeRightsImg.setRightsId(tradeRights.getId());
                tradeRightsImg.setTradeId(tradeRights.getTradeId());
                tradeRightsImg.setRightsImg(rightsImgData.getRightsImg());
                tradeRightsImgs.add(tradeRightsImg);
            }
            tradeRightsImgRepository.saveBatch(tradeRightsImgs);
        }
        TradeRightsLog tradeRightsLog = new TradeRightsLog();
        tradeRightsLog.setRightsId(tradeRights.getId());
        tradeRightsLog.setState(TradeRightsEndStateEnum.用户修改申请.getCode());
        tradeRightsLog.setContent("用户修改了售后申请。");
        tradeRightsLogRepository.save(tradeRightsLog);
    }

    @Override
    public BbcTradeRightsVO.GoodsTotalVO goodsTotal(BbcTradeRightsBuildDTO.GoodsTotalDTO dto) {
        if (!ObjectUtil.isAllNotEmpty(dto, dto.getTradeId(), dto.getSkuIds())) {
            throw new BusinessException("参数不能为空");
        }
        List<String> skuIds = dto.getSkuIds();
        BbcTradeRightsVO.GoodsTotalVO goodsTotalVO = new BbcTradeRightsVO.GoodsTotalVO();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal point = BigDecimal.ZERO;
        for (String skuId : skuIds) {
            QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
            query.eq("trade_id", dto.getTradeId());
            query.eq("sku_id", skuId);
            TradeGoods tradeGoods = tradeGoodsRepository.getOne(query);
            if (ObjectUtil.isEmpty(tradeGoods)) {
                throw new BusinessException("未查到订单商品信息");
            }
            if (ObjectUtil.isNotEmpty(tradeGoods.getTradeAmount())) {
                amount = amount.add(tradeGoods.getTradeAmount());
            }
            if (ObjectUtil.isNotEmpty(tradeGoods.getTradePointAmount())) {
                point = point.add(tradeGoods.getTradePointAmount());
            }
        }
        goodsTotalVO.setAmount(amount);
        goodsTotalVO.setPoint(point);
        return goodsTotalVO;
    }

    @Override
    public void twoCheck(BbcTradeRightsDTO.IdDTO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空!");
        }
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtil.isEmpty(tradeRights)) {
            throw new BusinessException("未查询到售后数据!");
        }
        if (tradeRights.getState().equals(TradeRightsEndStateEnum.买家二次申诉.getCode())) {
            throw new BusinessException("申诉已提交，请耐心等待审核结果。");
        }

        if (!tradeRights.getState().equals(TradeRightsEndStateEnum.商户驳回.getCode())) {
            throw new BusinessException("售后未处于商家驳回状态");
        }
        tradeRights.setState(TradeRightsEndStateEnum.买家二次申诉.getCode());
        tradeRights.setCheckState(TradeRightsNewStateEnum.待审核.getCode());
        repository.updateById(tradeRights);
        TradeRightsLog tradeRightsLog = new TradeRightsLog();
        tradeRightsLog.setRightsId(tradeRights.getId());
        tradeRightsLog.setState(TradeRightsEndStateEnum.买家二次申诉.getCode());
        tradeRightsLog.setContent("用户进行二次申诉。");
        tradeRightsLogRepository.save(tradeRightsLog);
    }

    /**
     * 组装TradeVO、tradeGoodsVO数据
     *
     * @param tradeRights
     */
    private BbcTradeRightsVO.ListVO fillTradeVO(TradeRights tradeRights) {
/*        QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
        tradeRightsGoodsQueryWrapper.eq("rights_id", tradeVO.getId());
        List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);
        BbcShopVO.DetailVO shop = iBbcShopRpc.detailShop(new BbcShopDTO.IdDTO(tradeVO.getShopId()));
        BeanUtils.copyProperties(shop, tradeVO);
        List<BbcTradeRightsVO.TradeRightsGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
            TradeGoods tradeGoods = tradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());

            BbcTradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbcTradeRightsVO.TradeRightsGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeRightsGoodsVO);
            BeanUtils.copyProperties(tradeRightsGoods, tradeRightsGoodsVO);
            tradeRightsGoodsVO.setQuantity(tradeRightsGoods.getQuantity());
            tradeRightsGoodsVO.setRefundAmount(tradeRightsGoods.getRefundAmount());
            BeanUtils.copyProperties(shop, tradeRightsGoodsVO);
            tradeGoodsVOS.add(tradeRightsGoodsVO);

        }
        CommonShopVO.ShopServiceOutVO shopService = iCommonShopRpc.getShopService(tradeVO.getShopId());
        if (ObjectUtils.isNotEmpty(shopService)) {
*//*            tradeVO.setQqNumber(shopService.getAccount());
            tradeVO.setShopPhone(shopService.getPhone());*//*
        }
        tradeVO.setTradeRightsGoodsVOS(tradeGoodsVOS);*/
        BbcTradeRightsVO.ListVO listVO = new BbcTradeRightsVO.ListVO();
        listVO.setStateDetails(TradeRightsEndStateEnum.getRemarkByCode(tradeRights.getState()));
        BeanUtil.copyProperties(tradeRights, listVO);
        if (!tradeRights.getState().equals(TradeRightsEndStateEnum.商家确认收货并退款.getCode())) {
            listVO.setRefundAmount(tradeRights.getShouldRefundAmount());
            listVO.setRefundPoint(tradeRights.getShouldRefundPoint());
        } else {
            BeanUtil.copyProperties(tradeRights, listVO);
        }
        BbcShopVO.InnerDetailVO detailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(tradeRights.getShopId()));
        listVO.setShopName(detailVO.getShopName());
        listVO.setCdate(tradeRights.getCdate());
        listVO.setRecvFullAddres(tradeRights.getRecvFullAddres());
        List<BbcTradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsVOS = new ArrayList<>();
        QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
        query.eq("rights_id", tradeRights.getId());
        List<TradeRightsGoods> rightsGoodsList = tradeRightsGoodsRepository.list(query);
        for (TradeRightsGoods tradeRightsGoods : rightsGoodsList) {
            BbcTradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbcTradeRightsVO.TradeRightsGoodsVO();
            BeanUtil.copyProperties(tradeRightsGoods, tradeRightsGoodsVO);
            tradeRightsGoodsVO.setGoodsId(tradeRightsGoods.getGoodsId());
            BbcTradeRightsVO.GoodsInfo goodsInfo = iBbcGoodsInfoRpc.selectOne(tradeRightsGoods.getGoodsId());
            if (ObjectUtil.isNotEmpty(goodsInfo)) {
                tradeRightsGoodsVO.setGoodsTitle(goodsInfo.getGoodsTitle());
                tradeRightsGoodsVO.setGoodsPriceUnit(goodsInfo.getGoodsPriceUnit());
                tradeRightsGoodsVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "{}" : getImage(goodsInfo.getGoodsImage()));
                tradeRightsGoodsVOS.add(tradeRightsGoodsVO);
            }
        }
        listVO.setTradeRightsGoodsVO(tradeRightsGoodsVOS);
        return listVO;
    }

    private void fillShop(BbcTradeRightsVO.ListVO tradeVO) {
        BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
        //innerShopQTO.setShopId(tradeVO.getShopId());
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
        if (ObjectUtils.isNotEmpty(innerDetailVO)) {
            tradeVO.setShopName(innerDetailVO.getShopName());
            /*            tradeVO.setShopLogo(innerDetailVO.getShopLogo());*/
        }
    }

    private String getImage(String images) {
        if (images != null && !images.equals("{}")) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }
}
