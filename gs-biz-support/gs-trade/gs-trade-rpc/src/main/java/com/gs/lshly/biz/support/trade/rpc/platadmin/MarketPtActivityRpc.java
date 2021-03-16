package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.MarketPtActivityGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtActivityRpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zdf
* @since 2020-11-28
*/
@DubboService
public class MarketPtActivityRpc implements IMarketPtActivityRpc{
    @Autowired
    private IMarketPtActivityService MarketPtActivityService;

    @Override
    public PageData<MarketPtActivityVO.ListVO> pageData(MarketPtActivityQTO.QTO qto){
        return MarketPtActivityService.pageData(qto);
    }


    @Override
    public void addMarketPtActivity(MarketPtActivityDTO.ETO eto){
        MarketPtActivityService.addMarketPtActivity(eto);
    }

    @Override
    public void deleteMarketPtActivity(MarketPtActivityDTO.IdListDTO dto){
        MarketPtActivityService.deleteMarketPtActivity(dto);
    }


    @Override
    public void editMarketPtActivity(MarketPtActivityDTO.ETO eto){
        MarketPtActivityService.editMarketPtActivity(eto);
    }

    @Override
    public MarketPtActivityVO.DetailVO detailMarketPtActivity(MarketPtActivityDTO.IdDTO dto){
        return  MarketPtActivityService.detailMarketPtActivity(dto);
    }

    @Override
    public List<MarketPtActivityVO.ListVO> list() {
        return MarketPtActivityService.list();
    }


}