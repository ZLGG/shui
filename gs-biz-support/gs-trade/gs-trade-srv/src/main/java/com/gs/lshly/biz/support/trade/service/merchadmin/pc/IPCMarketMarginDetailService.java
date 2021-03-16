package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

public interface IPCMarketMarginDetailService {
    PageData<TradeMarginDetailVO.ListVO> PageData(TradeMarginDetailQTO.typeQTO qto);

    TradeMarginDetailVO.ListVO detailTradeMargin(TradeMarginDetailDTO.IdDTO dto);

    TradeMarginVO.ListVO view(TradeMarginDetailQTO.viewQTO qto);

}
