package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCancelDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;

/**
*
* @author oy
* @since 2020-11-21
*/
public interface ITradeCancelRpc {

    PageData<TradeCancelVO.ListVO> pageData(TradeCancelQTO.QTO qto);

    TradeCancelVO.DetailVO detailTradeCancel(TradeCancelDTO.IdDTO dto);

    ExportDataDTO export(TradeCancelQTO.IdListQTO qo)  throws Exception;
}