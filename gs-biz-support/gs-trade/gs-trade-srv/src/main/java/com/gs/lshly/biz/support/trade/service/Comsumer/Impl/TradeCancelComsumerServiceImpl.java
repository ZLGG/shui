package com.gs.lshly.biz.support.trade.service.Comsumer.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.middleware.mq.aliyun.ConSumerService.TradeCancelComsumerService;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TradeCancelComsumerServiceImpl implements TradeCancelComsumerService {
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeCancelRepository iTradeCancelRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @Override
    public void TradeTimeOutCancel(String tradeId) {
        if (ObjectUtils.isEmpty(tradeId)){
            throw new BusinessException("没有订单");
        }
        Trade trade = iTradeRepository.getById(tradeId);
        if (trade.getTradeState()==TradeStateEnum.待支付.getCode()) {
        trade.setTradeState(TradeStateEnum.已取消.getCode());
        trade.setTimeoutCancel(TradeTimeOutCancelEnum.超时取消.getCode());
        iTradeRepository.updateById(trade);
        //回库存
        cancelTradeReturnStock(trade.getId());
        TradeCancel tradeCancel = new TradeCancel();
        fillTradeCancel(new BbbH5TradeCancelDTO.CancelDTO(trade.getId(),60,"超时取消"),tradeCancel,trade,null,TradeCancelStateEnum.完成.getCode(),TradeCancelRefundStateEnum.无需退款.getCode());
        iTradeCancelRepository.save(tradeCancel);
        }

    }

    /**
     * 取消交易,回库存
     * @param tradeId
     * @return
     */
    private boolean cancelTradeReturnStock(String tradeId) {
        List<CommonStockDTO.InnerChangeStockItem> goodsItemList = new ArrayList<>();
        fillChangeStockItem(tradeId, goodsItemList);
        return commonStockRpc.innerPlusStockForTrade(goodsItemList);
    }

    private void fillTradeCancel(BbbH5TradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel, Trade trade, String tradePayId,Integer cancelState,Integer cancelRefundState) {
        tradeCancel.setTradeId(trade.getId());
        tradeCancel.setTradeCode(trade.getTradeCode());
        tradeCancel.setUserId(trade.getUserId());
        tradeCancel.setShopId(trade.getShopId());
        tradeCancel.setPayId(tradePayId);
        tradeCancel.setTradeAmount(trade.getTradeAmount());
        tradeCancel.setCancelState(cancelState);
        tradeCancel.setApplyType(TradeCancelApplyTypeEnum.用户取消订单.getCode());
        tradeCancel.setApplyTime(LocalDateTime.now());
        tradeCancel.setReasonType(dto.getReasonType());
        tradeCancel.setRemark(dto.getRemark());
        tradeCancel.setRefundState(cancelRefundState);
    }
    /**
     * 填充skuId/购买数量
     * @param tradeId
     * @param goodsItemList
     */
    private void fillChangeStockItem(String tradeId, List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeId);
        List<TradeGoods> tradeGoodsList = iTradeGoodsRepository.list(tradeGoodsQueryWrapper);
        for(TradeGoods tradeGoods : tradeGoodsList){
            CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
            innerChangeStockItem.setSkuId(tradeGoods.getSkuId());
            innerChangeStockItem.setQuantity(tradeGoods.getQuantity());
            goodsItemList.add(innerChangeStockItem);
        }
    }
}
