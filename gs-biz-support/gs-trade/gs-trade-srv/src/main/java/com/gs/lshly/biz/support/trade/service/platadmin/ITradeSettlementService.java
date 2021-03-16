package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

public interface ITradeSettlementService {

    PageData<TradeSettlementVO.ListVO> pageData(TradeSettlementQTO.settlementList qto);

    void addTradeSettlement(TradeSettlementDTO.ETO eto);

    TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto);

    boolean summaryOrderData();

    String settleConfirmation(String tradeCode);

}