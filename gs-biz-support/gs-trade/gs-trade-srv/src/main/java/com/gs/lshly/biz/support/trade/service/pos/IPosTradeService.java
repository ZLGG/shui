package com.gs.lshly.biz.support.trade.service.pos;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.*;


public interface IPosTradeService {
    ResponseData<Void> pullTrade(PosRSPurchaseSyncRequestDTO dto,String param);

    void orderState(PosTradeStateRequestDTO.DTO posTradeStateRequestDTO);

    void orderRight(PosTradeRightRequestDTO.DTO posTradeRightRequestDTO);

    void addOrderRight(PosTradeOOnlineOrderRequestDTO.DTO posTradeOOnlineOrderRequestDTO);

    void FinishOrderRight(PosFinishAndCancelRequestDTO.DTO dto);

    void CancelOrderRight(PosFinishAndCancelRequestDTO.DTO dto);

    void addTrade(PosTradeODeliverOrderRequestDTO.DTO dto);

    void finishTrade(PosFinishAndCancelTradeRequestDTO.DTO dto);

    void cancelTrade(PosFinishAndCancelTradeRequestDTO.DTO dto);
}
