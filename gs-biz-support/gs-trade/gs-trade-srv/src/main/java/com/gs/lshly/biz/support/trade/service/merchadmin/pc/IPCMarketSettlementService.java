package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

import java.util.List;

public interface IPCMarketSettlementService {
    PageData<TradeSettlementVO.ListVO> settlementPageData(TradeSettlementQTO.PcQTO qto);

    TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto);

    List<TradeSettlementDetailVO.ListVO> DownloadExport(TradeSettlementQTO.DownloadExportQTO qo);


}
