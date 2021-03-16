package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;

/**
*
* @author zst
* @since 2020-12-10
*/
public interface ITradeMarginDetailRpc {

    PageData<TradeMarginDetailVO.ListVO> pageData(TradeMarginDetailQTO.QTO qto);

    ExportDataDTO export(TradeMarginDetailQTO.IdListQTO qo) throws Exception;
}