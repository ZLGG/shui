package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface ITradeRpc {

    PageData<TradeListVO.tradeVO> tradeListPageData(TradeQTO.TradeList qto);


    TradeListVO.tradeVO detail(TradeDTO.IdDTO dto);

    ExportDataDTO export(TradeQTO.IdListQTO qo) throws Exception;
}