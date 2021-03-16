package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeInvoiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeInvoiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeInvoiceListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;

public interface IPCMerchTradeInvoiceService {

    PageData<PCMerchTradeInvoiceListVO.ListVO> invoiceListPageData(PCMerchTradeInvoiceQTO.QTO qto);

    Boolean issueInvoice(PCMerchTradeInvoiceQTO.IssueQTO qto);

    Boolean mailInvoice(PCMerchTradeInvoiceQTO.MailQTO qto);

    void saveInvReqData(PCMerchTradeInvoiceDTO.DTO dto);

}