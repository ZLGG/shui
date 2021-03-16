package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsWatchDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsWatchQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsWatchVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockLogisticsWatchRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsWatchService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zzg
* @since 2020-10-24
*/
@DubboService
public class StockLogisticsWatchRpc implements IStockLogisticsWatchRpc{
    @Autowired
    private IStockLogisticsWatchService  StockLogisticsWatchService;

    @Override
    public PageData<StockLogisticsWatchVO.ListVO> pageData(StockLogisticsWatchQTO.QTO qto){
        return StockLogisticsWatchService.pageData(qto);
    }

    @Override
    public void addStockLogisticsWatch(StockLogisticsWatchDTO.ETO eto){
        StockLogisticsWatchService.addStockLogisticsWatch(eto);
    }

    @Override
    public PageData<StockLogisticsWatchVO.ListVO> listWatch(StockLogisticsWatchQTO.QTO qto) {

        return StockLogisticsWatchService.listWatch(qto);
    }


}