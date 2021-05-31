package com.gs.lshly.biz.support.trade.service.bbc.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.enums.TradeRightsGoodsTypeEnum;
import com.gs.lshly.biz.support.trade.enums.TradeRightsNewStateEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
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


    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    @DubboReference
    private IBbcShopRpc iBbcShopRpc;

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;

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
    public void addTradeRights(BbcTradeRightsBuildDTO.ETO dto) {
        if (StringUtils.isEmpty(dto.getRightsRemark())) {
            throw new BusinessException("请填写售后原因");
        }
        //根据订单id查询订单数据
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("订单不存在");
        } else if (trade.getTradeState().intValue() != TradeStateEnum.已完成.getCode()) {
            throw new BusinessException("请确认收货后再申请售后");
        }
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.eq("trade_id", dto.getTradeId());
        //后状态不等于驳回
        TradeRights tradeRights = repository.getOne(query);
        if (ObjectUtil.isNotEmpty(tradeRights) && !tradeRights.getState().equals(TradeRightsStateEnum.驳回.getCode())) {
            throw new BusinessException("已经在执行售后操作");
        }
        //根据订单id以及订单商品id查询商品数据
        BigDecimal refundAmount = BigDecimal.ZERO;
        BigDecimal refundPoint = BigDecimal.ZERO;
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
/*            for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
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
            }*/
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
            rightsGoods.setOrderCode(trade.getTradeCode());
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

        //根据售后类型新增售后记录
        TradeRights rights = new TradeRights();
        if (ObjectUtil.isNotEmpty(tradeRights)) {
            rights.setIsTwoCheck(1);
            rights.setCheckState(TradeRightsNewStateEnum.待审核.getCode());
        }
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
        rights.setState(TradeRightsStateEnum.申请.getCode());
        rights.setRightsType(dto.getRightsType());
        rights.setRightsReasonType(dto.getRightsReasonType());
        rights.setRightsRemark(dto.getRightsRemark());
        rights.setRefundRemarks(dto.getRightsRemark());
        rights.setReturnType(dto.getReturnType());
        rights.setApplyTime(LocalDateTime.now());
        rights.setRefundMoneyType(TradeRightsRefundMoneyTypeEnum.售后申请退款.getCode());
        rights.setRefundType(TradeRightsRefundTypeEnum.原路退回.getCode());
        rights.setIsHide(0);
        repository.save(rights);
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

    }

    @Override
    public BbcTradeRightsVO.DetailVO detailTradeRights(BbcTradeRightsDTO.IdDTO dto) {
        TradeRights tradeRights = repository.getById(dto.getId());
        BbcTradeRightsVO.DetailVO detailVo = new BbcTradeRightsVO.DetailVO();
        if (ObjectUtils.isEmpty(tradeRights)) {
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRights, detailVo);
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
            detailVo.setTradeRightsGoodsVOS(list);
        }
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(tradeRights.getShopId()));
        if (ObjectUtils.isNotEmpty(innerDetailVO)) {
            detailVo.setShopName(innerDetailVO.getShopName());
        }
        CommonShopVO.ShopServiceOutVO shopService = iCommonShopRpc.getShopService(tradeRights.getShopId());
        if (ObjectUtils.isNotEmpty(shopService)) {
            detailVo.setQqNumber(shopService.getAccount());
            detailVo.setShopPhone(shopService.getPhone());
        }
        QueryWrapper<TradeRightsImg> query1 = MybatisPlusUtil.query();
        query1.and(i -> i.eq("rights_id", tradeRights.getId()));
        List<TradeRightsImg> list = tradeRightsImgRepository.list(query1);
        if (ObjectUtils.isNotEmpty(list)) {
            detailVo.setTradeRightImg(ListUtil.getIdList(TradeRightsImg.class, list, "rightsImg"));
        }
        return detailVo;

    }

    @Override
    public PageData<BbcTradeRightsVO.ListVO> tradeRightsListData(BbcTradeRightsQTO.RightsList qto) {
        QueryWrapper<BbcTradeRightsQTO.RightsList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("`user_id`", qto.getJwtUserId()));
        wrapper.and(i -> i.ne("`is_hide`", 1));
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
        wrapper.orderByDesc("cdate");

        IPage<BbcTradeRightsVO.ListVO> page = MybatisPlusUtil.pager(qto);
        IPage<BbcTradeRightsVO.ListVO> voiPage = repository.selectTradeRightsList(page, wrapper);

        List<BbcTradeRightsVO.ListVO> voList = new ArrayList<>();
        for (BbcTradeRightsVO.ListVO tradeVO : voiPage.getRecords()) {
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            voList.add(tradeVO);
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
        if (tradeRights.getState().equals(TradeRightsStateEnum.通过.getCode())) {
            tradeRights.setReturnGoodsLogisticsName(dto.getReturnGoodsLogisticsName());
            tradeRights.setReturnGoodsLogisticsNum(dto.getReturnGoodsLogisticsNum());
            tradeRights.setReturnGoodsLogisticsDate(LocalDateTime.now());
            tradeRights.setState(TradeRightsStateEnum.已退货.getCode());
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
                if (tradeRights.getState().equals(TradeRightsStateEnum.已发货.getCode()) && tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())) {
                    tradeRights.setState(TradeRightsStateEnum.完成.getCode());
                } else {
                    throw new BusinessException("不能修改状态");
                }
            } else {
                throw new BusinessException("查询不到售后单");
            }
            repository.updateById(tradeRights);

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
        //删除售后商品表数据
        tradeRightsGoodsRepository.remove(Wrappers.<TradeRightsGoods>lambdaQuery().eq(TradeRightsGoods::getRightsId, tradeRights.getId()));
        //删除售后表数据
        tradeRights.setState(TradeRightsStateEnum.用户取消.getCode());
        repository.removeById(tradeRights);
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

    /**
     * 组装TradeVO、tradeGoodsVO数据
     *
     * @param tradeVO
     */
    private void fillTradeVO(BbcTradeRightsVO.ListVO tradeVO) {
        QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
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
            tradeVO.setQqNumber(shopService.getAccount());
            tradeVO.setShopPhone(shopService.getPhone());
        }
        tradeVO.setTradeRightsGoodsVOS(tradeGoodsVOS);
    }

    private void fillShop(BbcTradeRightsVO.ListVO tradeVO) {
        BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
        innerShopQTO.setShopId(tradeVO.getShopId());
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
        if (ObjectUtils.isNotEmpty(innerDetailVO)) {
            tradeVO.setShopName(innerDetailVO.getShopName());
            tradeVO.setShopLogo(innerDetailVO.getShopLogo());
        }
    }

}
