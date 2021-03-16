package com.gs.lshly.rpc.api.bbb.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceAddressVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;

/**
*
* @author zst
* @since 2021-01-15
*/
public interface IH5BbbTradeInvoiceAddressRpc {

    PageData<H5BbbTradeInvoiceAddressVO.ListVO> pageData(H5BbbTradeInvoiceAddressQTO.IdQTO qto);

    void addTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto);

    void deleteTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto);


    void editTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto);

    H5BbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto);

    void updateByIsDefault(PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto);
}