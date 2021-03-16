package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeCancelVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeCancelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-20
*/
@DubboService
public class H5MerchTradeCancelRpc implements IH5MerchTradeCancelRpc{
    @Autowired
    private IH5MerchTradeCancelService  h5MerchTradeCancelService;

    @Override
    public PageData<H5MerchTradeCancelVO.ListVO> pageData(H5MerchTradeCancelQTO.QTO qto){
        return h5MerchTradeCancelService.pageData(qto);
    }

    @Override
    public void addTradeCancel(H5MerchTradeCancelDTO.ETO eto){
        h5MerchTradeCancelService.addTradeCancel(eto);
    }

    @Override
    public void deleteTradeCancel(H5MerchTradeCancelDTO.IdDTO dto){
        h5MerchTradeCancelService.deleteTradeCancel(dto);
    }


    @Override
    public void editTradeCancel(H5MerchTradeCancelDTO.ETO eto){
        h5MerchTradeCancelService.editTradeCancel(eto);
    }

    @Override
    public H5MerchTradeCancelVO.DetailVO detailTradeCancel(H5MerchTradeCancelDTO.IdDTO dto){
        return  h5MerchTradeCancelService.detailTradeCancel(dto);
    }

}