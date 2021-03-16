package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockShopLogisticsCorpVO;

public interface IStockShopLogisticsCorpService {

    PageData<StockShopLogisticsCorpVO.ListVO> pageData(StockShopLogisticsCorpQTO.QTO qto);

    void addStockShopLogisticsCorp(StockShopLogisticsCorpDTO.ETO eto);

    void deleteStockShopLogisticsCorp(StockShopLogisticsCorpDTO.IdDTO dto);


    void editStockShopLogisticsCorp(StockShopLogisticsCorpDTO.ETO eto);

    StockShopLogisticsCorpVO.DetailVO detailStockShopLogisticsCorp(StockShopLogisticsCorpDTO.IdDTO dto);

}