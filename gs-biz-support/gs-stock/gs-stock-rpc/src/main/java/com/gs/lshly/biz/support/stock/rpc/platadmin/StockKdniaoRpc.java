package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockKdniaoQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockKdniaoVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockKdniaoRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockKdniaoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-29
*/
@DubboService
public class StockKdniaoRpc implements IStockKdniaoRpc{
    @Autowired
    private IStockKdniaoService  StockKdniaoService;

    @Override
    public PageData<StockKdniaoVO.ListVO> pageData(StockKdniaoQTO.QTO qto){
        return StockKdniaoService.pageData(qto);
    }

    @Override
    public void addStockKdniao(StockKdniaoDTO.ETO eto){
        StockKdniaoService.addStockKdniao(eto);
    }

    @Override
    public void deleteStockKdniao(StockKdniaoDTO.IdDTO dto){
        StockKdniaoService.deleteStockKdniao(dto);
    }


    @Override
    public void editStockKdniao(StockKdniaoDTO.ETO eto){
        StockKdniaoService.editStockKdniao(eto);
    }

    @Override
    public StockKdniaoVO.DetailVO detailStockKdniao(StockKdniaoDTO.IdDTO dto){
        return  StockKdniaoService.detailStockKdniao(dto);
    }

}