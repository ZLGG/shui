package com.gs.lshly.rpc.api.bbb.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;

/**
*
* @author zst
* @since 2021-01-15
*/
public interface IH5BbbTradeInvoiceRpc {

    PageData<H5BbbTradeInvoiceVO.ListVO> pageData(H5BbbTradeInvoiceQTO.IdQTO qto);

    void addTradeInvoice(H5BbbTradeInvoiceDTO.AddETO eto);

    void deleteTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto);


    void editTradeInvoice(H5BbbTradeInvoiceDTO.EditETO eto);

    H5BbbTradeInvoiceVO.DetailVO detailTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto);

    H5BbbTradeInvoiceVO.DetailVO detailInvoice(H5BbbTradeInvoiceQTO.IdQTO idQTO);

    H5BbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(H5BbbTradeInvoiceQTO.QTO qto);

    void updateByIsDefaultList(H5BbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO dto);

    H5BbbTradeInvoiceVO.DetailVO detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO dto);
}