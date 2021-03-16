package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCompanyCodeDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCompanyCodeQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCompanyCodeVO;

/**
*
* @author zzg
* @since 2020-10-30
*/
public interface IStockLogisticsCompanyCodeRpc {

    PageData<StockLogisticsCompanyCodeVO.ListVO> pageData(StockLogisticsCompanyCodeQTO.QTO qto);

    void addStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto);

    void deleteStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto);


    void editStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto);

    StockLogisticsCompanyCodeVO.DetailVO detailStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto);

    StockLogisticsCompanyCodeVO.ListCodeCodeVO provideLogisticsCode();
}