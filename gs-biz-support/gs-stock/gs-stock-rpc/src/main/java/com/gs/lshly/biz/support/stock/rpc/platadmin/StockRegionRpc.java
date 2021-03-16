package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockRegionDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockRegionQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockRegionVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockRegionRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockRegionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zzg
* @since 2020-10-24
*/
@DubboService
public class StockRegionRpc implements IStockRegionRpc{
    @Autowired
    private IStockRegionService  StockRegionService;

    @Override
    public PageData<StockRegionVO.ListVO> pageData(StockRegionQTO.QTO qto){
        return StockRegionService.pageData(qto);
    }

    @Override
    public void addStockRegion(StockRegionDTO.ETO eto){
        StockRegionService.addStockRegion(eto);
    }

    @Override
    public void deleteStockRegion(StockRegionDTO.IdDTO dto){
        StockRegionService.deleteStockRegion(dto);
    }


    @Override
    public void editStockRegion(StockRegionDTO.ETO eto){
        StockRegionService.editStockRegion(eto);
    }

    @Override
    public StockRegionVO.DetailVO detailStockRegion(StockRegionDTO.IdDTO dto){
        return  StockRegionService.detailStockRegion(dto);
    }

}