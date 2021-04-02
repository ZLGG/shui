package com.gs.lshly.biz.support.trade.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;

public interface IPCBbbTradeInvoiceService {

    PageData<PCBbbTradeInvoiceVO.ListVO> pageData(PCBbbTradeInvoiceQTO.QTO qto);

    void addTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto);

    PCBbbTradeInvoiceVO.ChooseVO choose(PCBbbTradeInvoiceDTO.ETO eto);

    void deleteTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto);

    void editTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto);

    PCBbbTradeInvoiceVO.DetailVO detailTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto);

    void updateByIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto);

    PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(PCBbbTradeInvoiceDTO.ETO qto);

    void updateByIsDefaultList(PCBbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO eto);

    void updateByAddressIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto);

    PCBbbTradeInvoiceVO.clickBillingVO clickBilling(PCBbbTradeInvoiceDTO.clickBilingDTO dto);
}