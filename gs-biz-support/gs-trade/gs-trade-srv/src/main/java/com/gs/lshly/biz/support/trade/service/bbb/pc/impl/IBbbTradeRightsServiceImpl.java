package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbTradeRightsService;
import com.gs.lshly.common.enums.TradeComplaintStateEnum;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class IBbbTradeRightsServiceImpl implements IBbbTradeRightsService {
    @Autowired
    private ITradeRightsRepository repository;
    @Autowired
    private  ITradeRepository tradeRepository;
    @Autowired
    private  ITradeGoodsRepository tradeGoodsRepository;
    @Autowired
    private  ITradeRightsGoodsRepository tradeRightsGoodsRepository;
    @Autowired
    private  ITradeRightsImgRepository tradeRightsImgRepository;
    @Autowired
    private ITradeComplaintRepository iTradeComplaintRepository;
    @DubboReference
    private IBbbShopRpc iBbbShopRpc;
    @Override
    public PageData<BbbTradeRightsVO.ListVO> tradeRightsListData(BbbTradeRightsQTO.RightsList qto) {
        QueryWrapper<BbbTradeRightsQTO.RightsList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("`user_id`",qto.getJwtUserId()));
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

        IPage<BbbTradeRightsVO.ListVO> page = MybatisPlusUtil.pager(qto);
        repository.selectBbbTradeRightsList(page,wrapper);

        List<BbbTradeRightsVO.ListVO> voList = new ArrayList<>();
        for(BbbTradeRightsVO.ListVO tradeVO : page.getRecords()){
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(tradeVO.getShopId(), new BaseDTO());
            if (ObjectUtils.isNotEmpty(complexDetailVO)){
                tradeVO.setShopName(complexDetailVO.getShopName());
            }
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public BbbTradeRightsVO.DetailVO detailTradeRights(BbbTradeRightsDTO.IdDTO idDTO) {
        TradeRights tradeRights = repository.getById(idDTO.getId());
        BbbTradeRightsVO.DetailVO detailVo = new BbbTradeRightsVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRights, detailVo);
        QueryWrapper< TradeRightsGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("rights_id",tradeRights.getId()));
        TradeRightsGoods one = tradeRightsGoodsRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            List<BbbTradeRightsVO.TradeRightsGoodsVO>list=  new ArrayList<>();
            BbbTradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbbTradeRightsVO.TradeRightsGoodsVO();
            BeanUtils.copyProperties(one,tradeRightsGoodsVO);
            list.add(tradeRightsGoodsVO);
            detailVo.setTradeRightsGoodsVOS(list);
        }
        return detailVo;
    }

    @Override
    public void addTradeRights(BbbTradeRightsBuildDTO.ETO dto) {
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
        for(BbbTradeRightsBuildDTO.ETO.ProductData productData : dto.getProductData()){
            TradeGoods tradeGoods = tradeGoodsRepository.getById(productData.getTradeGoodsId());
            if(ObjectUtils.isEmpty(tradeGoods)){
                throw new BusinessException("订单商品数据不存在");
            }
            //根据订单id以及订单商品id查询已申请售后数据 判断是否可以申请售后(售后状态不等于驳回或已退货数量与购买数量一致则不允许申请售后)
            QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
            tradeRightsGoodsQueryWrapper.eq("trade_id",dto.getTradeId());
            tradeRightsGoodsQueryWrapper.eq("trade_goods_id",productData.getTradeGoodsId());
            List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);
            if (ObjectUtils.isNotEmpty(tradeRightsGoodsList)) {
                for (TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList) {
                    if (tradeRightsGoods.getTradeGoodsId().equals(productData.getTradeGoodsId())){
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
        List<BbbTradeRightsBuildDTO.ETO.RightsImgData> rightsImgDataList = dto.getRightsImgData();
        if(ObjectUtils.isNotEmpty(rightsImgDataList)){
            List<TradeRightsImg> tradeRightsImgs = new ArrayList<>();
            for(BbbTradeRightsBuildDTO.ETO.RightsImgData rightsImgData : rightsImgDataList){
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
    public void addAddress(BbbTradeRightsBuildDTO.AddAddressDTO dto) {
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
    public void confirmReceipt(BbbTradeRightsDTO.IdDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())){
            TradeRights tradeRights = repository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(tradeRights)){
                if (tradeRights.getState().equals(TradeRightsStateEnum.已发货.getCode()) && tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())){
                    tradeRights.setState(TradeRightsStateEnum.完成.getCode());
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
    public void applyAgain(BbbTradeRightsBuildDTO.ApplyAgain dto) {
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

    /**
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(BbbTradeRightsVO.ListVO tradeVO) {
        QueryWrapper<TradeRightsGoods> tradeRightsGoodsQueryWrapper = new QueryWrapper<>();
        tradeRightsGoodsQueryWrapper.eq("rights_id",tradeVO.getId());
        List<TradeRightsGoods> tradeRightsGoodsList = tradeRightsGoodsRepository.list(tradeRightsGoodsQueryWrapper);

        List<BbbTradeRightsVO.TradeRightsGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeRightsGoods tradeRightsGoods : tradeRightsGoodsList){
            TradeGoods tradeGoods = tradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());
            BbbTradeRightsVO.TradeRightsGoodsVO tradeRightsGoodsVO = new BbbTradeRightsVO.TradeRightsGoodsVO();
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
}
