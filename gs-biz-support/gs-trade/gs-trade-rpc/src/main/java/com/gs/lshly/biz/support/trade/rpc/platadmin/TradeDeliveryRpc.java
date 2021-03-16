package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradeDeliveryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDeliveryDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeDeliveryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class TradeDeliveryRpc implements ITradeDeliveryRpc{
    @Autowired
    private ITradeDeliveryService  TradeDeliveryService;

    @Override
    public PageData<TradeDeliveryVO.ListVO> pageData(TradeDeliveryQTO.QTO qto){
        return TradeDeliveryService.pageData(qto);
    }

    @Override
    public void addTradeDelivery(TradeDeliveryDTO.ETO eto){
        TradeDeliveryService.addTradeDelivery(eto);
    }

    @Override
    public void deleteTradeDelivery(TradeDeliveryDTO.IdDTO dto){
        TradeDeliveryService.deleteTradeDelivery(dto);
    }


    @Override
    public void editTradeDelivery(TradeDeliveryDTO.ETO eto){
        TradeDeliveryService.editTradeDelivery(eto);
    }

    @Override
    public TradeDeliveryVO.DetailVO detailTradeDelivery(TradeDeliveryDTO.IdDTO dto){
        return  TradeDeliveryService.detailTradeDelivery(dto);
    }

}