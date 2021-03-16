package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeInvoiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeInvoiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeInvoiceListVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IPCMerchTradeInvoiceRpc {

    PageData<PCMerchTradeInvoiceListVO.ListVO> invoiceListPageData(PCMerchTradeInvoiceQTO.QTO qto);

    Boolean issueInvoice(PCMerchTradeInvoiceQTO.IssueQTO qto);

    Boolean mailInvoice(PCMerchTradeInvoiceQTO.MailQTO qto);

    /**
    *用户提交开票申请时，调用此RPC方法，将发票初始数据存入数据表中
     **/
    void saveInvReqData(PCMerchTradeInvoiceDTO.DTO dto);

}