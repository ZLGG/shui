package com.gs.lshly.biz.support.trade.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeInvoiceRpc;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeInvoiceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2020-12-24
*/
@DubboService
public class PCBbbTradeInvoiceRpc implements IPCBbbTradeInvoiceRpc{
    @Autowired
    private IPCBbbTradeInvoiceService  pCBbbTradeInvoiceService;

    @Override
    public PageData<PCBbbTradeInvoiceVO.ListVO> pageData(PCBbbTradeInvoiceQTO.QTO qto){
        return pCBbbTradeInvoiceService.pageData(qto);
    }

    @Override
    public void addTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto){
        pCBbbTradeInvoiceService.addTradeInvoice(eto);
    }

    @Override
    public PCBbbTradeInvoiceVO.ChooseVO choose(PCBbbTradeInvoiceDTO.ETO eto){
        return pCBbbTradeInvoiceService.choose(eto);
    }

    @Override
    public void deleteTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto){
        pCBbbTradeInvoiceService.deleteTradeInvoice(dto);
    }

    @Override
    public void editTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto){
        pCBbbTradeInvoiceService.editTradeInvoice(eto);
    }

    @Override
    public PCBbbTradeInvoiceVO.DetailVO detailTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto){
        return  pCBbbTradeInvoiceService.detailTradeInvoice(dto);
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO dto){
        pCBbbTradeInvoiceService.updateByIsDefault(dto);
    }

    @Override
    public PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(PCBbbTradeInvoiceDTO.ETO qto){
        return pCBbbTradeInvoiceService.applyInvoiceList(qto);
    }

    @Override
    public void updateByIsDefaultList(PCBbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO dto){
        pCBbbTradeInvoiceService.updateByIsDefaultList(dto);
    }

    @Override
    public void updateByAddressIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {
        pCBbbTradeInvoiceService.updateByAddressIsDefault(eto);
    }

    @Override
    public PCBbbTradeInvoiceVO.clickBillingVO clickBilling(PCBbbTradeInvoiceDTO.clickBilingDTO dto) {
        return pCBbbTradeInvoiceService.clickBilling(dto);
    }
}