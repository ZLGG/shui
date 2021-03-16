package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCancelQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCancelVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeCancelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-20
*/
@DubboService
public class BbbH5TradeCancelRpc implements IBbbH5TradeCancelRpc {

    @Autowired
    private IBbbH5TradeCancelService bbcTradeCancelService;

    @Override
    public PageData<BbbH5TradeCancelVO.ListVO> pageData(BbbH5TradeCancelQTO.QTO qto){
        return bbcTradeCancelService.pageData(qto);
    }

    @Override
    public void addTradeCancel(BbbH5TradeCancelDTO.ETO eto){
        bbcTradeCancelService.addTradeCancel(eto);
    }

    @Override
    public void deleteTradeCancel(BbbH5TradeCancelDTO.IdDTO dto){
        bbcTradeCancelService.deleteTradeCancel(dto);
    }


    @Override
    public void editTradeCancel(BbbH5TradeCancelDTO.ETO eto){
        bbcTradeCancelService.editTradeCancel(eto);
    }

    @Override
    public BbbH5TradeCancelVO.DetailVO detailTradeCancel(BbbH5TradeCancelDTO.IdDTO dto){
        return  bbcTradeCancelService.detailTradeCancel(dto);
    }

}