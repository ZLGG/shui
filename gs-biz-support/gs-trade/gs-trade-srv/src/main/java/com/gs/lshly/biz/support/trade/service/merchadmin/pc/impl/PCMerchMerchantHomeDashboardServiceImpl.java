package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMerchantHomeDashboardService;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.foundation.IPCMerchDataNoticeRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
public class PCMerchMerchantHomeDashboardServiceImpl  implements IPCMerchMerchantHomeDashboardService {
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;
    @Autowired
    private ITradeCommentRepository iTradeCommentRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;

    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IPCMerchShopRpc ipcMerchShopRpc;
    @DubboReference
    private IPCMerchDataNoticeRpc ipcMerchDataNoticeRpc;

    @Override
    public MerchantHomeDashboardVO.ListVO HomeDashboard(MerchantHomeDashboardDTO.ETO dto) {
        //我的工作台
        int count = getTradeCount(TradeStateEnum.待发货.getCode(),dto.getJwtShopId());
        MerchantHomeDashboardVO.ListVO listVO = new MerchantHomeDashboardVO.ListVO();
        listVO.setConsignmentTradeCount(count);
        listVO.setPendingTrade(count);//交易->待发货订单
        QueryWrapper<MerchantHomeDashboardDTO.ETO> queryWrapper =MybatisPlusUtil.query();
        queryWrapper.and(i->i.eq("shop_id",dto.getJwtShopId()));
        queryWrapper.and(i->i.in("trade_state",TradeStateEnum.待发货.getCode(),TradeStateEnum.待收货.getCode(),TradeStateEnum.已完成.getCode()));
        List<Trade> trades=iTradeRepository.yesterdayTrade(queryWrapper);
        if (ObjectUtils.isNotEmpty(trades)){
            listVO.setYesterdayTradeCount(trades.size());
            listVO.setTradeCount(trades.size());//昨日店铺转化下单数
            BigDecimal totalTrade=new BigDecimal(0);
            for (Trade trade:trades){
                if(ObjectUtils.isNotEmpty(trade.getTradeAmount())){
                    totalTrade=totalTrade.add(trade.getTradeAmount());
                }

            }
            listVO.setYesterdayTradeAmount(totalTrade);
            listVO.setSalesVolume(totalTrade);//昨日店铺转化的销售额
        }
        listVO.setYesterdayUV(ipcMerchShopRpc.innUV(dto.getJwtShopId()));
        listVO.setVisitCount(ipcMerchShopRpc.innPV(dto.getJwtShopId()));
        //店铺信息
        if (StringUtils.isNotBlank(dto.getJwtShopId())){
            PCMerchShopVO.ShopSimpleVO shopSimpleVO = ipcMerchShopRpc.innerShopSimple(dto.getJwtShopId());
            if (ObjectUtils.isNotEmpty(shopSimpleVO)){
                listVO.setShopLogo(shopSimpleVO.getShopLogo());
                listVO.setShopDesc(shopSimpleVO.getShopDesc());
                listVO.setMerchantName(shopSimpleVO.getShopName());
                listVO.setShopShareCode(StringUtils.isBlank(shopSimpleVO.getShareCode())?"":shopSimpleVO.getShareCode());

            }
        }
        //昨日店铺转化
        //交易
        int noPayTradeCount = getTradeCount(TradeStateEnum.待支付.getCode(),dto.getJwtShopId());
        listVO.setNoPayTrade(noPayTradeCount);
        int receivedTradeCount =  getTradeCount(TradeStateEnum.待收货.getCode(),dto.getJwtShopId());
        listVO.setReceivedTrade(receivedTradeCount);
        //今日订单
        QueryWrapper<Trade> query2 = MybatisPlusUtil.query();
        query2.and(i->i.ne("trade_state",10));
        query2.and(i->i.ne("trade_state",50));
        query2.and(i->i.eq("TO_DAYS(cdate)","TO_DAYS(now())"));
        List<Trade> list = iTradeRepository.list(query2);
        listVO.setTodayTrade(ObjectUtils.isNotEmpty(list)? list.size() : 0);
        //售后
        int pendingReturnCount= getTradeRights(TradeRightsStateEnum.通过.getCode(),TradeRightsTypeEnum.换货.getCode(),dto.getJwtShopId());
        listVO.setPendingReturn(pendingReturnCount);
        int pendingExchangeCount= getTradeRights(TradeRightsStateEnum.通过.getCode(),TradeRightsTypeEnum.退货退款.getCode(),dto.getJwtShopId());
        listVO.setPendingExchange(pendingExchangeCount);
        //商品
        PCMerchGoodsInfoVO.HomeCountGoodsVO homeCountGoodsVO = ipcMerchAdminGoodsInfoRpc.innerHomeCountGoodsVO(dto);
        if (ObjectUtils.isNotEmpty(homeCountGoodsVO)){

            listVO.setNoSaleGoods(ObjectUtils.isNotEmpty(homeCountGoodsVO.getUnderGoodsNum())?homeCountGoodsVO.getUnderGoodsNum():0);
            listVO.setInventoryWarning(ObjectUtils.isNotEmpty(homeCountGoodsVO.getStockAlarmNum())?homeCountGoodsVO.getStockAlarmNum():0);
            listVO.setExamineFail(ObjectUtils.isNotEmpty(homeCountGoodsVO.getCheckFaildNum())?homeCountGoodsVO.getCheckFaildNum():0);
            listVO.setGoodsConsultation(ObjectUtils.isNotEmpty(homeCountGoodsVO.getQaGoodsNum())?homeCountGoodsVO.getQaGoodsNum():0);
        }
        QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(dto.getJwtShopId())){
            query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        }
        int goodsCommentCount = iTradeCommentRepository.count(query);
        listVO.setGoodsComment(goodsCommentCount);
        QueryWrapper<MerchantHomeDashboardDTO.ETO> query1 = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(dto.getJwtShopId())){
            query1.and(i->i.eq("g.shop_id",dto.getJwtShopId()));
        }
        query1.and(i->i.in("t.trade_state",TradeStateEnum.待发货.getCode(),TradeStateEnum.待收货.getCode(),TradeStateEnum.已完成.getCode()));
        List<MerchantHomeDashboardVO.GoodsInfo>  goodsInfos=iTradeGoodsRepository.selectGoodsInfo(query1);
        goodsInfos.sort(Comparator.comparing(MerchantHomeDashboardVO.GoodsInfo::getGoodsSalesVolume).reversed());
        listVO.setGoodsInfos(goodsInfos);
        PCMerchDataNoticeDTO.innerDTO baseDTO = new PCMerchDataNoticeDTO.innerDTO();
        baseDTO.setJwtShopId(dto.getJwtShopId());
        List<PCMerchDataNoticeVO.ListVO> listVOS = ipcMerchDataNoticeRpc.innerList(baseDTO);
        if (ObjectUtils.isNotEmpty(listVOS)){
            listVO.setNotices(listVOS);
        }
        return listVO;
    }

    private int getTradeRights(Integer state, Integer type,String jwtShopId) {
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i->i.eq("state",state));
        query.and(i->i.eq("rights_type",type));
        if (StringUtils.isNotBlank(jwtShopId)){
            query.and(i->i.eq("shop_id",jwtShopId));
        }

        return iTradeRightsRepository.count(query);
    }

    private int getTradeCount(Integer code, String jwtShopId) {
        QueryWrapper<Trade> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_state",code));
        if (StringUtils.isNotBlank(jwtShopId)){
            query.and(i->i.eq("shop_id",jwtShopId));
        }
        return  iTradeRepository.count(query);
    }

}
