package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface ITradePayRpc {

    PageData<TradePayVO.ListVO> pageData(TradePayQTO.QTO qto);

    TradePayVO.DetailVO detailTradePay(TradePayDTO.IdDTO dto);

    PageData<TradePayVO.RelationDetailVO> relationList(TradePayQTO.RelationQTO qto);

    TradePayVO.DetailVO relationGet(TradePayDTO.IdDTO idDTO);

    ExportDataDTO export(TradePayDTO.IdsDTO qo) throws Exception;

    ExportDataDTO payExport(TradePayQTO.IdListQTO qo)throws Exception ;

    void delete(TradePayQTO.IdListQTO ids);
}