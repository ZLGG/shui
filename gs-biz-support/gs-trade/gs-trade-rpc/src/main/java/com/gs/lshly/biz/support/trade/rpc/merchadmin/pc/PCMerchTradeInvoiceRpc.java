package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeInvoiceService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeInvoiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeInvoiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeInvoiceListVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeInvoiceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author tangxu
* @since 2020-12-14
*/
@DubboService
public class PCMerchTradeInvoiceRpc implements IPCMerchTradeInvoiceRpc{

    @Autowired
    private IPCMerchTradeInvoiceService iPCMerchTradeInvoiceService;

    @Override
    public PageData<PCMerchTradeInvoiceListVO.ListVO> invoiceListPageData(PCMerchTradeInvoiceQTO.QTO qto) {
        return iPCMerchTradeInvoiceService.invoiceListPageData(qto);
    }

    @Override
    public Boolean issueInvoice(PCMerchTradeInvoiceQTO.IssueQTO qto) {
        return iPCMerchTradeInvoiceService.issueInvoice(qto);
    }

    @Override
    public Boolean mailInvoice(PCMerchTradeInvoiceQTO.MailQTO qto) {
        return iPCMerchTradeInvoiceService.mailInvoice(qto);
    }

    @Override
    public void saveInvReqData(PCMerchTradeInvoiceDTO.DTO dto) {
        iPCMerchTradeInvoiceService.saveInvReqData(dto);
    }
}