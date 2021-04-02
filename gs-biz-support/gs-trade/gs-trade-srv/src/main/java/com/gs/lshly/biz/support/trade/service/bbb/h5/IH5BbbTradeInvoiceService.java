package com.gs.lshly.biz.support.trade.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;

public interface IH5BbbTradeInvoiceService {

    PageData<H5BbbTradeInvoiceVO.ListVO> pageData(H5BbbTradeInvoiceQTO.IdQTO qto);

    void addTradeInvoice(H5BbbTradeInvoiceDTO.AddETO eto);

    void deleteTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto);


    void editTradeInvoice(H5BbbTradeInvoiceDTO.EditETO eto);

    H5BbbTradeInvoiceVO.DetailVO detailTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto);

    H5BbbTradeInvoiceVO.DetailVO detailInvoice(H5BbbTradeInvoiceQTO.IdQTO dto);

    H5BbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(H5BbbTradeInvoiceQTO.QTO qto);

    void updateByIsDefaultList(H5BbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO eto);

    H5BbbTradeInvoiceVO.DetailVO detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO qto);
}
