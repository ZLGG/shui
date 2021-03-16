package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeGoodsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class H5MerchTradeGoodsRpc implements IH5MerchTradeGoodsRpc{
    @Autowired
    private IH5MerchTradeGoodsService  h5MerchTradeGoodsService;

    @Override
    public PageData<H5MerchTradeGoodsVO.ListVO> pageData(H5MerchTradeGoodsQTO.QTO qto){
        return h5MerchTradeGoodsService.pageData(qto);
    }

    @Override
    public void addTradeGoods(H5MerchTradeGoodsDTO.ETO eto){
        h5MerchTradeGoodsService.addTradeGoods(eto);
    }

    @Override
    public void deleteTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto){
        h5MerchTradeGoodsService.deleteTradeGoods(dto);
    }


    @Override
    public void editTradeGoods(H5MerchTradeGoodsDTO.ETO eto){
        h5MerchTradeGoodsService.editTradeGoods(eto);
    }

    @Override
    public H5MerchTradeGoodsVO.DetailVO detailTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto){
        return  h5MerchTradeGoodsService.detailTradeGoods(dto);
    }

}