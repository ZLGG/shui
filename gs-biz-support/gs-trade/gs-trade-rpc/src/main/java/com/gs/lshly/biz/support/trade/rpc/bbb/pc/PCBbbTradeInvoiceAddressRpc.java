package com.gs.lshly.biz.support.trade.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceAddressVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeInvoiceAddressRpc;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeInvoiceAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2020-12-24
*/
@DubboService
public class PCBbbTradeInvoiceAddressRpc implements IPCBbbTradeInvoiceAddressRpc{
    @Autowired
    private IPCBbbTradeInvoiceAddressService  pCBbbTradeInvoiceAddressService;

    @Override
    public PageData<PCBbbTradeInvoiceAddressVO.ListVO> pageData(PCBbbTradeInvoiceAddressQTO.QTO qto){
        return pCBbbTradeInvoiceAddressService.pageData(qto);
    }

    @Override
    public void addTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto){
        pCBbbTradeInvoiceAddressService.addTradeInvoiceAddress(eto);
    }

    @Override
    public void deleteTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto){
        pCBbbTradeInvoiceAddressService.deleteTradeInvoiceAddress(dto);
    }

    @Override
    public void editTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto){
        pCBbbTradeInvoiceAddressService.editTradeInvoiceAddress(eto);
    }

    @Override
    public PCBbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto){
        return  pCBbbTradeInvoiceAddressService.detailTradeInvoiceAddress(dto);
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto){
        pCBbbTradeInvoiceAddressService.updateByIsDefault(eto);
    }
}