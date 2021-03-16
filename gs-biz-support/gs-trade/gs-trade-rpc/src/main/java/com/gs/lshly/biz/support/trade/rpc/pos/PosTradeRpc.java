package com.gs.lshly.biz.support.trade.rpc.pos;

import com.gs.lshly.biz.support.trade.service.pos.IPosTradeService;
import com.gs.lshly.common.response.ResponseData;

import com.gs.lshly.common.struct.pos.dto.*;

import com.gs.lshly.rpc.api.pos.IPosTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PosTradeRpc implements IPosTradeRpc {

    @Autowired
    private IPosTradeService iPosTradeService;
    @Override
    public ResponseData<Void> pullTrade(PosRSPurchaseSyncRequestDTO dto,String param) {
        return iPosTradeService.pullTrade(dto,param);
    }

    @Override
    public void orderState(PosTradeStateRequestDTO.DTO posTradeStateRequestDTO) {
        iPosTradeService.orderState(posTradeStateRequestDTO);
    }

    @Override
    public void orderRight(PosTradeRightRequestDTO.DTO posTradeRightRequestDTO) {
        iPosTradeService.orderRight(posTradeRightRequestDTO);
    }

    @Override
    public void addOrderRight(PosTradeOOnlineOrderRequestDTO.DTO posTradeOOnlineOrderRequestDTO) {
        iPosTradeService.addOrderRight(posTradeOOnlineOrderRequestDTO);
    }

    @Override
    public void FinishOrderRight(PosFinishAndCancelRequestDTO.DTO dto) {
        iPosTradeService.FinishOrderRight(dto);
    }

    @Override
    public void CancelOrderRight(PosFinishAndCancelRequestDTO.DTO dto) {
        iPosTradeService.CancelOrderRight(dto);
    }

    @Override
    public void addTrade(PosTradeODeliverOrderRequestDTO.DTO dto) {
        iPosTradeService.addTrade(dto);
    }

    @Override
    public void finishTrade(PosFinishAndCancelTradeRequestDTO.DTO dto) {
        iPosTradeService.finishTrade(dto);
    }

    @Override
    public void cancelTrade(PosFinishAndCancelTradeRequestDTO.DTO dto) {
        iPosTradeService.cancelTrade(dto);
    }
}
