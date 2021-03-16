package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeService;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserRpc;
import com.lakala.boss.api.common.Common;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
public class H5MerchTradeServiceImpl implements IH5MerchTradeService {

    @Autowired
    private ITradeRepository repository;
    @Autowired
    private ITradeDeliveryRepository tradeDeliveryRepository;
    @DubboReference
    private ICommonUserRpc commonUserRpc;
    @Autowired
    private ITradeRepository tradeRepository;
    @Autowired
    private ITradeGoodsRepository tradeGoodsRepository;
    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;
    @DubboReference
    private IH5MerchUserRpc ih5MerchUserRpc;


    @Override
    public void editOrderAmount(H5MerchTradeDTO.orderAmountOrFreight dto) {
        if (StringUtils.isBlank(dto.getId())){
            throw new BusinessException("请传入订单ID");
        }
        Trade trade = tradeRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("没有查询到订单");
        }
        if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            if (ObjectUtils.isNotEmpty(dto.getOrderAmount())){
                //修改交易总金额
                trade.setTradeAmount(dto.getOrderAmount());
            }
            if (ObjectUtils.isNotEmpty(dto.getFreight())){
                //修改运费
                BigDecimal differencePrice=dto.getFreight().subtract(trade.getDeliveryAmount());
                trade.setDeliveryAmount(dto.getFreight());
                trade.setTradeAmount(trade.getTradeAmount().add(differencePrice));
            }
        }
        trade.setChangePriceCause(dto.getChangePriceCause());
        tradeRepository.saveOrUpdate(trade);

    }

    @Override
    public void editComment(H5MerchTradeDTO.editComment dto) {
        Trade trade = tradeRepository.getById(dto.getId());
        BeanUtils.copyProperties(dto, trade);
        tradeRepository.updateById(trade);

    }

    @Override
    public H5MerchTradeListVO.tradeVO detail(H5MerchTradeDTO.IdDTO dto) {
        H5MerchTradeListVO.tradeVO tradeVO = new H5MerchTradeListVO.tradeVO();
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }
        BeanUtils.copyProperties(trade, tradeVO);
        //填充商家信息
        fillShop(tradeVO);
        fillTradeVO(tradeVO);
        if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
        }
        //查询物流信息
        if(tradeVO.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) &&
                (tradeVO.getTradeState().equals(TradeStateEnum.待收货.getCode()) ||
                        tradeVO.getTradeState().equals(TradeStateEnum.已完成.getCode()))){//快递配送/已发货/已收货
            //填充物流信息
            fillLogisticsCompany(tradeVO);
        }
        //填充用户信息
        fillUserInfo(tradeVO);

        return tradeVO;
    }
    private void fillUserInfo(H5MerchTradeListVO.tradeVO tradeVO) {
        H5MerchUserVO.UserSimpleVO userSimpleVO = ih5MerchUserRpc.innerUserSimple(tradeVO.getUserId());
        if(ObjectUtils.isNotEmpty(userSimpleVO)){
            tradeVO.setUserName(userSimpleVO.getUserName());
        }
    }
    private void fillLogisticsCompany(H5MerchTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
        tradeDeliveryQueryWrapper.eq("trade_id", tradeVO.getId());
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeDelivery)){
            tradeVO.setDeliveryRemark(tradeDelivery.getDeliveryRemark());
            CommonLogisticsCompanyVO.DetailVO logisticsDetailVO = commonLogisticsCompanyRpc.getLogisticsCompany(tradeDelivery.getLogisticsId());
            if(ObjectUtils.isNotEmpty(logisticsDetailVO)){
                tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
                tradeVO.setLogisticsCompanyCode(logisticsDetailVO.getCode());
                tradeVO.setLogisticsCompanyName(logisticsDetailVO.getName());
            }
        }
    }
    private void fillShop(H5MerchTradeListVO.tradeVO tradeVO) {
        CommonShopVO.SimpleVO innerDetailVO = commonShopRpc.shopDetails(tradeVO.getShopId());
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            tradeVO.setShopName(innerDetailVO.getShopName());
        }
    }

    @Override
    public PageData<H5MerchTradeListVO.tradeVO> tradeListPageData(H5MerchTradeQTO.TradeList qto) {
        QueryWrapper<H5MerchTradeQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`shop_id`",qto.getJwtShopId()));
        if(ObjectUtils.isNotEmpty(qto.getCreateTime())){
            wrapper.and(i -> i.eq("",qto.getCreateTime()));
        }
        if(StringUtils.isNotBlank(qto.getTradeCode())){
            wrapper.and(i -> i.eq("t.`trade_code`",qto.getTradeCode()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPersonName())){
            wrapper.and(i -> i.eq("t.`recv_person_name`",qto.getRecvPersonName()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPhone())){
            wrapper.and(i -> i.eq("t.`recv_phone`",qto.getRecvPhone()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getSourceType())){
            wrapper.and(i -> i.eq("t.`source_type`",qto.getSourceType()));//区分2b2c
        }
        if(ObjectUtils.isNotEmpty(qto.getDeliveryType())){
            wrapper.and(i -> i.eq("t.`delivery_type`",qto.getDeliveryType()));
        }
        if(StringUtils.isNotBlank(qto.getUserName())){
            CommonUserVO.DetailVO userDetailVO = commonUserRpc.detailsByUserName(qto.getUserName());
            if(ObjectUtils.isNotEmpty(userDetailVO) && StringUtils.isNotBlank(userDetailVO.getId())){
                //查询用户id
                wrapper.and(i -> i.eq("t.`user_id`",userDetailVO.getId()));
            }
        }

        wrapper.orderByDesc("cdate");

        IPage<H5MerchTradeListVO.tradeVO> page = MybatisPlusUtil.pager(qto);

        tradeRepository.selectH5MerchTradePage(page,wrapper);

        List<H5MerchTradeListVO.tradeVO> voList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(page) ) {
            for (H5MerchTradeListVO.tradeVO tradeVO : page.getRecords()) {
                //查询店铺信息
            CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(tradeVO.getShopId());
            if (ObjectUtils.isNotEmpty(simpleVO)) {
                tradeVO.setShopName(simpleVO.getShopName());
            }
                //根据交易ID查询交易商品集合
                fillTradeVO(tradeVO);
                if (tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
                    tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
                }
                voList.add(tradeVO);
            }
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }
    /**
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(H5MerchTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<H5MerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(tradeGoodsList)) {
            for (TradeGoods tradeGoods : tradeGoodsList) {
                H5MerchTradeListVO.TradeGoodsVO tradeGoodsVO = new H5MerchTradeListVO.TradeGoodsVO();
                BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
                tradeGoodsVO.setShopName(tradeVO.getShopName());
                tradeGoodsVOS.add(tradeGoodsVO);
            }
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }
}
