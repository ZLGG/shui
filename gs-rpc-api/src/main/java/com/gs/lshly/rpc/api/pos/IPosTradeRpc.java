package com.gs.lshly.rpc.api.pos;

import com.gs.lshly.common.response.ResponseData;

import com.gs.lshly.common.struct.pos.dto.*;


public interface IPosTradeRpc {
    ResponseData<Void> pullTrade(PosRSPurchaseSyncRequestDTO dto,String param);

    void orderState(PosTradeStateRequestDTO.DTO posTradeStateRequestDTO);

    void orderRight(PosTradeRightRequestDTO.DTO posTradeRightRequestDTO);

    //2c退换货申请单
    void addOrderRight(PosTradeOOnlineOrderRequestDTO.DTO posTradeOOnlineOrderRequestDTO);

    //2c退换货完成
    void FinishOrderRight(PosFinishAndCancelRequestDTO.DTO dto);

    //2c退换货取消
    void CancelOrderRight(PosFinishAndCancelRequestDTO.DTO dto);

    //2c创建线上订单
    void addTrade(PosTradeODeliverOrderRequestDTO.DTO dto);

    //2c完成订单
    void finishTrade(PosFinishAndCancelTradeRequestDTO.DTO dto);

    //2c取消订单
    void cancelTrade(PosFinishAndCancelTradeRequestDTO.DTO dto);
}
