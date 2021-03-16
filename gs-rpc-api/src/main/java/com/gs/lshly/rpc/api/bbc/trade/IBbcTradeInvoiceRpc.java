package com.gs.lshly.rpc.api.bbc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeInvoiceVO;

/**
*
* @author zst
* @since 2021-01-15
*/
public interface IBbcTradeInvoiceRpc {

    PageData<BbcTradeInvoiceVO.BbcListVO> pageData(BbcTradeInvoiceQTO.Ids qto);

    void addTradeInvoice(BbcTradeInvoiceDTO.AddETO eto);

    void deleteTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto);


    void editTradeInvoice(BbcTradeInvoiceDTO.EditETO eto);

    BbcTradeInvoiceVO.DetailVO detailTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto);

}