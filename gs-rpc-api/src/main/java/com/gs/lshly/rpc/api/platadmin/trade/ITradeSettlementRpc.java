package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
*
* @author zst
* @since 2020-11-30
*/
public interface ITradeSettlementRpc {

    PageData<TradeSettlementVO.ListVO> pageData(TradeSettlementQTO.settlementList qto);

    void addTradeSettlement(TradeSettlementDTO.ETO eto);

    TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto);

    ExportDataDTO export(TradeSettlementQTO.settlementList qo) throws Exception;

    boolean summaryOrderData();

    String settleConfirmation(String id);

}