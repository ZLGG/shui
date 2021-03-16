package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradePayService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradePayVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradePayRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class H5MerchTradePayRpc implements IH5MerchTradePayRpc{
    @Autowired
    private IH5MerchTradePayService  h5MerchTradePayService;

    @Override
    public PageData<H5MerchTradePayVO.ListVO> pageData(H5MerchTradePayQTO.QTO qto){
        return h5MerchTradePayService.pageData(qto);
    }

    @Override
    public void addTradePay(H5MerchTradePayDTO.ETO eto){
        h5MerchTradePayService.addTradePay(eto);
    }

    @Override
    public void deleteTradePay(H5MerchTradePayDTO.IdDTO dto){
        h5MerchTradePayService.deleteTradePay(dto);
    }


    @Override
    public void editTradePay(H5MerchTradePayDTO.ETO eto){
        h5MerchTradePayService.editTradePay(eto);
    }

    @Override
    public H5MerchTradePayVO.DetailVO detailTradePay(H5MerchTradePayDTO.IdDTO dto){
        return  h5MerchTradePayService.detailTradePay(dto);
    }

}