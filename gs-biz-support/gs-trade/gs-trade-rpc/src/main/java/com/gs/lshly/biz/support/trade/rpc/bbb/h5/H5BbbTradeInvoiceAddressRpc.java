package com.gs.lshly.biz.support.trade.rpc.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceAddressVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IH5BbbTradeInvoiceAddressRpc;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IH5BbbTradeInvoiceAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-15
*/
@DubboService
public class H5BbbTradeInvoiceAddressRpc implements IH5BbbTradeInvoiceAddressRpc{
    @Autowired
    private IH5BbbTradeInvoiceAddressService  h5BbbTradeInvoiceAddressService;

    @Override
    public PageData<H5BbbTradeInvoiceAddressVO.ListVO> pageData(H5BbbTradeInvoiceAddressQTO.IdQTO qto){
        return h5BbbTradeInvoiceAddressService.pageData(qto);
    }

    @Override
    public void addTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto){
        h5BbbTradeInvoiceAddressService.addTradeInvoiceAddress(eto);
    }

    @Override
    public void deleteTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto){
        h5BbbTradeInvoiceAddressService.deleteTradeInvoiceAddress(dto);
    }


    @Override
    public void editTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto){
        h5BbbTradeInvoiceAddressService.editTradeInvoiceAddress(eto);
    }

    @Override
    public H5BbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto){
        return  h5BbbTradeInvoiceAddressService.detailTradeInvoiceAddress(dto);
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto){
        h5BbbTradeInvoiceAddressService.updateByIsDefault(eto);
    }

}