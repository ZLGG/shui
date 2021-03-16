package com.gs.lshly.rpc.api.bbb.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceAddressVO;

/**
*
* @author zst
* @since 2020-12-24
*/
public interface IPCBbbTradeInvoiceAddressRpc {

    PageData<PCBbbTradeInvoiceAddressVO.ListVO> pageData(PCBbbTradeInvoiceAddressQTO.QTO qto);

    void addTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto);

    void deleteTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto);

    void editTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto);

    PCBbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto);

    void updateByIsDefault(PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto);

}