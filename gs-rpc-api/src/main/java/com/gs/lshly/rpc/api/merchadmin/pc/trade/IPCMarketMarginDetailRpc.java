package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

public interface IPCMarketMarginDetailRpc {
    PageData<TradeMarginDetailVO.ListVO>  PageData(TradeMarginDetailQTO.typeQTO qto);

    TradeMarginDetailVO.ListVO detailTradeMargin(TradeMarginDetailDTO.IdDTO dto);

    TradeMarginVO.ListVO view(TradeMarginDetailQTO.viewQTO qto);

}
