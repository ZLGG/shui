package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ITradeGoodsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeGoodsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class TradeGoodsRpc implements ITradeGoodsRpc{
    @Autowired
    private ITradeGoodsService  TradeGoodsService;

    @Override
    public PageData<TradeGoodsVO.ListVO> pageData(TradeGoodsQTO.QTO qto){
        return TradeGoodsService.pageData(qto);
    }

    @Override
    public void addTradeGoods(TradeGoodsDTO.ETO eto){
        TradeGoodsService.addTradeGoods(eto);
    }

    @Override
    public void deleteTradeGoods(TradeGoodsDTO.IdDTO dto){
        TradeGoodsService.deleteTradeGoods(dto);
    }


    @Override
    public void editTradeGoods(TradeGoodsDTO.ETO eto){
        TradeGoodsService.editTradeGoods(eto);
    }

    @Override
    public TradeGoodsVO.DetailVO detailTradeGoods(TradeGoodsDTO.IdDTO dto){
        return  TradeGoodsService.detailTradeGoods(dto);
    }

}