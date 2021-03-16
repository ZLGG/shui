package com.gs.lshly.biz.support.trade.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeInvoiceVO;

public interface IBbcTradeInvoiceService {

    PageData<BbcTradeInvoiceVO.BbcListVO> pageData(BbcTradeInvoiceQTO.Ids qto);

    void addTradeInvoice(BbcTradeInvoiceDTO.AddETO eto);

    void deleteTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto);


    void editTradeInvoice(BbcTradeInvoiceDTO.EditETO eto);

    BbcTradeInvoiceVO.DetailVO detailTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto);

}