package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockLogisticsCorpRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsCorpService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zzg
* @since 2020-10-23
*/
@DubboService
public class StockLogisticsCorpRpc implements IStockLogisticsCorpRpc{
    @Autowired
    private IStockLogisticsCorpService  StockLogisticsCorpService;

    @Override
    public PageData<StockLogisticsCorpVO.ListVO> pageData(StockLogisticsCorpQTO.QTO qto){
        return StockLogisticsCorpService.pageData(qto);
    }

    @Override
    public void addStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto){
        StockLogisticsCorpService.addStockLogisticsCorp(eto);
    }

    @Override
    public void deleteStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto){
        StockLogisticsCorpService.deleteStockLogisticsCorp(dto);
    }


    @Override
    public void editStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto){
        StockLogisticsCorpService.editStockLogisticsCorp(eto);
    }

    @Override
    public StockLogisticsCorpVO.DetailVO detailStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto){
        return  StockLogisticsCorpService.detailStockLogisticsCorp(dto);
    }

    @Override
    public void initializeLogisticsCompany(StockLogisticsCorpDTO.ETO dto){
        StockLogisticsCorpService.initializeLogisticsCompany(dto);
    }
}