package com.gs.lshly.biz.support.trade.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeInvoiceRpc;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeInvoiceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-15
*/
@DubboService
public class BbcTradeInvoiceRpc implements IBbcTradeInvoiceRpc{
    @Autowired
    private IBbcTradeInvoiceService  bbcTradeInvoiceService;

    @Override
    public PageData<BbcTradeInvoiceVO.BbcListVO> pageData(BbcTradeInvoiceQTO.Ids qto){
        return bbcTradeInvoiceService.pageData(qto);
    }

    @Override
    public void addTradeInvoice(BbcTradeInvoiceDTO.AddETO eto){
        bbcTradeInvoiceService.addTradeInvoice(eto);
    }

    @Override
    public void deleteTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto){
        bbcTradeInvoiceService.deleteTradeInvoice(dto);
    }


    @Override
    public void editTradeInvoice(BbcTradeInvoiceDTO.EditETO eto){
        bbcTradeInvoiceService.editTradeInvoice(eto);
    }

    @Override
    public BbcTradeInvoiceVO.DetailVO detailTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto){
        return  bbcTradeInvoiceService.detailTradeInvoice(dto);
    }

}