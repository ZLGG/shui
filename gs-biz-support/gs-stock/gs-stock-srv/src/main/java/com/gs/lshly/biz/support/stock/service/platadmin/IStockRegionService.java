package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockRegionDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockRegionQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockRegionVO;

public interface IStockRegionService {

    PageData<StockRegionVO.ListVO> pageData(StockRegionQTO.QTO qto);

    void addStockRegion(StockRegionDTO.ETO eto);

    void deleteStockRegion(StockRegionDTO.IdDTO dto);


    void editStockRegion(StockRegionDTO.ETO eto);

    StockRegionVO.DetailVO detailStockRegion(StockRegionDTO.IdDTO dto);

}