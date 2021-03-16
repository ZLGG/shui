package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockRegionDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockRegionQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockRegionVO;

/**
*
* @author zzg
* @since 2020-10-24
*/
public interface IStockRegionRpc {

    PageData<StockRegionVO.ListVO> pageData(StockRegionQTO.QTO qto);

    void addStockRegion(StockRegionDTO.ETO eto);

    void deleteStockRegion(StockRegionDTO.IdDTO dto);


    void editStockRegion(StockRegionDTO.ETO eto);

    StockRegionVO.DetailVO detailStockRegion(StockRegionDTO.IdDTO dto);

}