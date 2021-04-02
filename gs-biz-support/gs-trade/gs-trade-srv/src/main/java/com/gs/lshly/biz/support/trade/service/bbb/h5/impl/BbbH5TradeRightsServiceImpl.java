package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeRightsService;
import com.gs.lshly.common.enums.TradeComplaintStateEnum;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BbbH5TradeRightsServiceImpl implements IBbbH5TradeRightsService {
    @Autowired
    private ITradeRightsRepository repository;
    @Autowired
    private ITradeRepository tradeRepository;
    @Autowired
    private ITradeGoodsRepository tradeGoodsRepository;
    @Autowired
    private ITradeRightsGoodsRepository tradeRightsGoodsRepository;
    @Autowired
    private ITradeRightsImgRepository tradeRightsImgRepository;
    @Autowired
    private ITradeComplaintRepository iTradeComplaintRepository;
    @DubboReference
    private IBbbH5ShopRpc iBbbH5ShopRpc;
    @DubboReference
    private ICommonShopRpc iCommonShopRpc;
    @DubboReference
    private IBbbH5GoodsInfoRpc iBbbH5GoodsInfoRpc;
    @Override
    public PageData<BbbH5TradeRightsVO.ListVO> tradeRightsListData(BbbH5TradeRightsQTO.RightsList qto) {
        QueryWrapper<BbbH5TradeRightsQTO.RightsList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("`user_id`",qto.getJwtUserId()));
        wrapper.and(i -> i.eq("1",1));
        if(ObjectUtils.isNotEmpty(qto.getRightsType())){
            if(qto.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())){
                wrapper.and(i -> i.eq("rights_type",qto.getRightsType()));
            }else {
                List<Integer> rightsType = new ArrayList();
                rightsType.add(TradeRightsTypeEnum.仅退款.getCode());
                rightsType.add(TradeRightsTypeEnum.退货退款.getCode());
                wrapper.and(i -> i.in("rights_type",rightsType));
            }
        }
        wrapper.orderByDesc("cdate");

        IPage<BbbH5TradeRightsVO.ListVO> page = MybatisPlusUtil.pager(qto);
        repository.selectBbbH5TradeRightsList(page,wrapper);

        List<BbbH5TradeRightsVO.ListVO> voList = new ArrayList<>();
        for(BbbH5TradeRightsVO.ListVO tradeVO : page.getRecords()){
            //根据交易ID查询交易商品集合
            BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(new BbbH5ShopQTO.InnerShopQTO(tradeVO.getShopId()));
            if (ObjectUtils.isNotEmpty(innerDetailVO)){
                tradeVO.setShopName(innerDetailVO.getShopName());
            }
            CommonShopVO.ShopServiceOutVO shopService = iCommonShopRpc.getShopService(tradeVO.getShopId());
            if (ObjectUtils.isNotEmpty(shopService)){
                tradeVO.setQqNumber(shopService.getAccount());
                tradeVO.setShopPhone(shopService.getPhone());
            }
            fillTradeVO(tradeVO);
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }
    /**
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(BbbH5TradeRightsVO.ListVO tradeVO) {
        QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
        tradeRightsGoodsQueryWrapper.eq("rights_id",tradeVO.getId());
        List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);

        List<BbbH5TradeRightsVO.TradeRightsGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList){
            TradeGoods tradeGoods = tradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());

            BbbH5TradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbbH5TradeRightsVO.TradeRightsGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeRightsGoodsVO);
            tradeRightsGoodsVO.setQuantity(tradeRightsGoods.getQuantity());
            tradeRightsGoodsVO.setRefundAmount(tradeRightsGoods.getRefundAmount());
            QueryWrapper<TradeComplaint> query = MybatisPlusUtil.query();
            query.and(i->i.eq(ObjectUtils.isNotEmpty(tradeGoods.getId()),"trade_goods_id",tradeGoods.getId()));
            query.and(i->i.eq(ObjectUtils.isNotEmpty(tradeGoods.getTradeId()),"trade_id",tradeGoods.getTradeId()));
            TradeComplaint one = iTradeComplaintRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                if (one.getHandleState()==20){
                    tradeRightsGoodsVO.setIsComplaint(10);
                }else {
                    tradeRightsGoodsVO.setIsComplaint(20);
                }
            } else {
                tradeRightsGoodsVO.setIsComplaint(20);
            }
            tradeGoodsVOS.add(tradeRightsGoodsVO);
        }
        tradeVO.setTradeRightsGoodsVOS(tradeGoodsVOS);
    }

    @Override
    public BbbH5TradeRightsVO.DetailVO detailTradeRights(BbbH5TradeRightsDTO.IdDTO idDTO) {
        TradeRights tradeRights = repository.getById(idDTO.getId());
        BbbH5TradeRightsVO.DetailVO detailVo = new BbbH5TradeRightsVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRights, detailVo);
        QueryWrapper< TradeRightsGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("rights_id",tradeRights.getId()));
        TradeRightsGoods one = tradeRightsGoodsRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            List<BbbH5TradeRightsVO.TradeRightsGoodsVO>list=  new ArrayList<>();
            BbbH5TradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbbH5TradeRightsVO.TradeRightsGoodsVO();
            BeanUtils.copyProperties(one,tradeRightsGoodsVO);
            BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO = iBbbH5GoodsInfoRpc.innerSimpleServiceVO(one.getSkuId());
            if (ObjectUtils.isNotEmpty(innerServiceVO)){
                tradeRightsGoodsVO.setSkuImg(innerServiceVO.getGoodsImage());
            }
            list.add(tradeRightsGoodsVO);
            detailVo.setTradeRightsGoodsVOS(list);
        }
        BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(new BbbH5ShopQTO.InnerShopQTO(tradeRights.getShopId()));
        if (ObjectUtils.isNotEmpty(innerDetailVO)){
            detailVo.setShopName(innerDetailVO.getShopName());
        }
        CommonShopVO.ShopServiceOutVO shopService = iCommonShopRpc.getShopService(tradeRights.getShopId());
        if (ObjectUtils.isNotEmpty(shopService)){
            detailVo.setQqNumber(shopService.getAccount());
            detailVo.setShopPhone(shopService.getPhone());
        }
        QueryWrapper<TradeRightsImg> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("rights_id",tradeRights.getId()));
        List<TradeRightsImg> list = tradeRightsImgRepository.list(query1);
        if (ObjectUtils.isNotEmpty(list)){
            detailVo.setTradeRightImg(ListUtil.getIdList(TradeRightsImg.class,list,"rightsImg"));
        }
        return detailVo;
    }

    @Override
    @Transactional
    public void addTradeRights(BbbH5TradeRightsBuildDTO.ETO dto) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(dto.getRightsRemark())){
            throw new BusinessException("请填写售后原因");
        }
        //根据订单id查询订单数据
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("订单不存在");
        }else if(trade.getTradeState().intValue() != TradeStateEnum.已完成.getCode()){
            throw new BusinessException("请确认收货后再申请售后");
        }
        //根据订单id以及订单商品id查询商品数据
        BigDecimal refundAmount = BigDecimal.ZERO;
        List<TradeRightsGoods> tradeRightsGoodsS = new ArrayList<>();
        for(BbbH5TradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()){
            TradeGoods tradeGoods = tradeGoodsRepository.getById(productData.getTradeGoodsId());
            if(ObjectUtils.isEmpty(tradeGoods)){
                throw new BusinessException("订单商品数据不存在");
            }
            //根据订单id以及订单商品id查询已申请售后数据 判断是否可以申请售后(售后状态不等于驳回或已退货数量与购买数量一致则不允许申请售后)
            QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
            tradeRightsGoodsQueryWrapper.eq("trade_goods_id",productData.getTradeGoodsId());
            List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);
            for(TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList){
                if(ObjectUtils.isNotEmpty(tradeRightsGoods)){
                    if (tradeRightsGoods.getTradeGoodsId().equals(productData.getTradeGoodsId())){
                        //查询售后表的状态
                        TradeRights byId = repository.getById(tradeRightsGoods.getRightsId());
                        if (ObjectUtils.isNotEmpty(byId)){
                            if (byId.getState()==TradeRightsStateEnum.驳回.getCode()){
                                QueryWrapper<TradeComplaint> query = MybatisPlusUtil.query();
                                query.and(i->i.eq("trade_goods_id",tradeRightsGoods.getTradeGoodsId()));
                                query.and(i->i.eq("trade_id",tradeRightsGoods.getTradeId()));
                                TradeComplaint one = iTradeComplaintRepository.getOne(query);
                                if (ObjectUtils.isNotEmpty(one)){
                                    if (one.getHandleState()==TradeComplaintStateEnum.投诉成功.getCode()){
                                        byId.setState(TradeRightsStateEnum.申请.getCode());
                                        return;
                                    }
                                }

                            }
                        }
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
            }
            BigDecimal amount =BigDecimal.ZERO;
            if (ObjectUtils.isNotEmpty(tradeGoods.getSalePrice())){
                amount = tradeGoods.getSalePrice().multiply(new BigDecimal(productData.getQuantity()));
            }
            refundAmount = refundAmount.add(amount);
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
            tradeRightsGoodsS.add(rightsGoods);
        }

        //根据售后类型新增售后记录
        TradeRights tradeRights = new TradeRights();
        tradeRights.setTradeId(trade.getId());
        tradeRights.setUserId(trade.getUserId());
        tradeRights.setShopId(trade.getShopId());
        tradeRights.setMerchantId(trade.getMerchantId());
        tradeRights.setOrderCode(trade.getTradeCode());
        tradeRights.setRefundAmount(refundAmount);
        tradeRights.setState(TradeRightsStateEnum.申请.getCode());//设置退款的状态申请->驳回->通过->已退货->收到退货->等待退款->退款完成->等待发货->已发货->确认收货->用户取消->完成
        tradeRights.setRightsType(dto.getRightsType());
        tradeRights.setRightsReasonType(dto.getRightsReasonType());
        tradeRights.setRightsRemark(dto.getRightsRemark());
        tradeRights.setReturnType(dto.getReturnType());
        tradeRights.setApplyTime(LocalDateTime.now());
        repository.save(tradeRights);
        //保存售后商品表
        for(TradeRightsGoods tradeRightsGoods : tradeRightsGoodsS){
            tradeRightsGoods.setRightsId(tradeRights.getId());
            tradeRightsGoodsRepository.save(tradeRightsGoods);
        }

        //保存售后凭证
        List<BbbH5TradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
        if(ObjectUtils.isNotEmpty(rightsImgDataList)){
            List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
            for(BbbH5TradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList){
                TradeRightsImg tradeRightsImg = new TradeRightsImg();
                tradeRightsImg.setRightsId(tradeRights.getId());
                tradeRightsImg.setTradeId(tradeRights.getTradeId());
                tradeRightsImg.setRightsImg(rightsImgData.getRightsImg());
                tradeRightsImgs.add(tradeRightsImg);
            }
            tradeRightsImgRepository.saveBatch(tradeRightsImgs);
        }

    }

    @Override
    public void addAddress(BbbH5TradeRightsBuildDTO.AddAddressDTO dto) {
        if (ObjectUtils.isEmpty(dto.getReturnGoodsLogisticsName())){
            throw new BusinessException("请填写物流公司名字");
        }
        if (ObjectUtils.isEmpty(dto.getReturnGoodsLogisticsNum())){
            throw new BusinessException("请填写物流单号");
        }
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtils.isNotEmpty(tradeRights)){
            if (tradeRights.getState().equals(TradeRightsStateEnum.通过.getCode()) && (tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())||tradeRights.getRightsType().equals(TradeRightsTypeEnum.退货退款.getCode()))){
                tradeRights.setReturnGoodsLogisticsName(dto.getReturnGoodsLogisticsName())
                        .setReturnGoodsLogisticsDate(LocalDateTime.now())
                        .setReturnGoodsLogisticsNum(dto.getReturnGoodsLogisticsNum())
                        .setState(TradeRightsStateEnum.已退货.getCode());
            }else {
                throw new BusinessException("订单状态不正确，不能添加寄回地址");
            }
        }
        repository.updateById(tradeRights);
    }

    @Override
    public void confirmReceipt(BbbH5TradeRightsDTO.IdDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())){
            TradeRights tradeRights = repository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(tradeRights)){
                if (tradeRights.getState().equals(TradeRightsStateEnum.已发货.getCode()) && tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())){
                    tradeRights.setState(TradeRightsStateEnum.确认收货.getCode());
                }else{
                    throw new BusinessException("不能修改状态");
                }
            }else {
                throw new BusinessException("查询不到售后单");
            }
            repository.updateById(tradeRights);
        }else {
            throw new BusinessException("请传售后表ID");
        }
    }

    @Override
    public void applyAgain(BbbH5TradeRightsBuildDTO.ApplyAgain dto) {
        //查询售后表的状态是不是驳回
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有查询到售后表");
        }
        if (tradeRights.getState()==TradeRightsStateEnum.驳回.getCode()){
            QueryWrapper<TradeComplaint> query = MybatisPlusUtil.query();
            query.and(i->i.eq(ObjectUtils.isNotEmpty(dto.getTradeGoodsId()),"trade_goods_id",dto.getTradeGoodsId()));
            query.and(i->i.eq(ObjectUtils.isNotEmpty(dto.getTradeId()),"trade_id",dto.getTradeId()));
            TradeComplaint one = iTradeComplaintRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                //判断投诉是不是通过了
                if (one.getHandleState().equals(TradeComplaintStateEnum.投诉成功.getCode())){
                    //修改售后表状态为申请
                    tradeRights.setState(TradeRightsStateEnum.申请.getCode());
                    repository.updateById(tradeRights);
                }else {
                    throw new BusinessException("平台投诉没有成功，不能进行操作");
                }
            }

        }else {
            throw new BusinessException("不能重新申请售后");
        }


    }
}
