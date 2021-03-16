package com.gs.lshly.biz.support.trade.rpc.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IH5BbbTradeInvoiceRpc;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IH5BbbTradeInvoiceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-15
*/
@DubboService
public class H5BbbTradeInvoiceRpc implements IH5BbbTradeInvoiceRpc{
    @Autowired
    private IH5BbbTradeInvoiceService  h5BbbTradeInvoiceService;

    @Override
    public PageData<H5BbbTradeInvoiceVO.ListVO> pageData(H5BbbTradeInvoiceQTO.IdQTO qto){
        return h5BbbTradeInvoiceService.pageData(qto);
    }

    @Override
    public void addTradeInvoice(H5BbbTradeInvoiceDTO.AddETO eto){
        h5BbbTradeInvoiceService.addTradeInvoice(eto);
    }

    @Override
    public void deleteTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto){
        h5BbbTradeInvoiceService.deleteTradeInvoice(dto);
    }


    @Override
    public void editTradeInvoice(H5BbbTradeInvoiceDTO.EditETO eto){
        h5BbbTradeInvoiceService.editTradeInvoice(eto);
    }

    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto){
        return  h5BbbTradeInvoiceService.detailTradeInvoice(dto);
    }

    @Override
    public H5BbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(H5BbbTradeInvoiceQTO.QTO qto){
        return h5BbbTradeInvoiceService.applyInvoiceList(qto);
    }


    @Override
    public void updateByIsDefaultList(H5BbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO dto){
        h5BbbTradeInvoiceService.updateByIsDefaultList(dto);
    }

    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO qto){
        return h5BbbTradeInvoiceService.detailByTradeId(qto);
    }
}