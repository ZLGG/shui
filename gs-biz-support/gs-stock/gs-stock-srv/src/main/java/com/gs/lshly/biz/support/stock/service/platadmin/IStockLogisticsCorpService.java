package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;

public interface IStockLogisticsCorpService {

    PageData<StockLogisticsCorpVO.ListVO> pageData(StockLogisticsCorpQTO.QTO qto);

    void addStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto);

    void deleteStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto);

    void editStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto);

    StockLogisticsCorpVO.DetailVO detailStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto);

    void initializeLogisticsCompany(StockLogisticsCorpDTO.QTO dto);
}