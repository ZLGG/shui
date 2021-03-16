package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradePayService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradePayVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradePayRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradePayRpc implements IPCMerchTradePayRpc{
    @Autowired
    private IPCMerchTradePayService  pCMerchTradePayService;

    @Override
    public PageData<PCMerchTradePayVO.ListVO> pageData(PCMerchTradePayQTO.QTO qto){
        return pCMerchTradePayService.pageData(qto);
    }

    @Override
    public void addTradePay(PCMerchTradePayDTO.ETO eto){
        pCMerchTradePayService.addTradePay(eto);
    }

    @Override
    public void deleteTradePay(PCMerchTradePayDTO.IdDTO dto){
        pCMerchTradePayService.deleteTradePay(dto);
    }


    @Override
    public void editTradePay(PCMerchTradePayDTO.ETO eto){
        pCMerchTradePayService.editTradePay(eto);
    }

    @Override
    public PCMerchTradePayVO.DetailVO detailTradePay(PCMerchTradePayDTO.IdDTO dto){
        return  pCMerchTradePayService.detailTradePay(dto);
    }

}