package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

public interface IPCMarketSettlementRpc {
    PageData<TradeSettlementVO.ListVO>  settlementPageData(TradeSettlementQTO.PcQTO qto);

    TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto);

    ExportDataDTO export(TradeSettlementQTO.PcQTO qo) throws Exception;

    ExportDataDTO DownloadExport(TradeSettlementQTO.DownloadExportQTO qo) throws Exception;


}
