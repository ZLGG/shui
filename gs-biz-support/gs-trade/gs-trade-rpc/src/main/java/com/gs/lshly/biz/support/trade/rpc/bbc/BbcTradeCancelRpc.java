package com.gs.lshly.biz.support.trade.rpc.bbc;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCancelQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCancelVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeCancelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-20
*/
@DubboService
public class BbcTradeCancelRpc implements IBbcTradeCancelRpc{
    @Autowired
    private IBbcTradeCancelService  bbcTradeCancelService;

    @Override
    public PageData<BbcTradeCancelVO.ListVO> pageData(BbcTradeCancelQTO.QTO qto){
        return bbcTradeCancelService.pageData(qto);
    }

    @Override
    public void addTradeCancel(BbcTradeCancelDTO.ETO eto){
        bbcTradeCancelService.addTradeCancel(eto);
    }

    @Override
    public void deleteTradeCancel(BbcTradeCancelDTO.IdDTO dto){
        bbcTradeCancelService.deleteTradeCancel(dto);
    }


    @Override
    public void editTradeCancel(BbcTradeCancelDTO.ETO eto){
        bbcTradeCancelService.editTradeCancel(eto);
    }

    @Override
    public BbcTradeCancelVO.DetailVO detailTradeCancel(BbcTradeCancelDTO.IdDTO dto){
        return  bbcTradeCancelService.detailTradeCancel(dto);
    }

}