package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;

/**
*
* @author zzg
* @since 2020-10-23
*/
public interface IStockLogisticsCorpRpc {

    PageData<StockLogisticsCorpVO.ListVO> pageData(StockLogisticsCorpQTO.QTO qto);

    void addStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto);

    void deleteStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto);


    void editStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto);

    StockLogisticsCorpVO.DetailVO detailStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto);

    void initializeLogisticsCompany(StockLogisticsCorpDTO.ETO dto);

}