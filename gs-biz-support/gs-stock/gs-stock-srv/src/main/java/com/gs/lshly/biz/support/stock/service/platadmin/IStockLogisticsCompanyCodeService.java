package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCompanyCode;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCompanyCodeDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCompanyCodeQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCompanyCodeVO;

import java.util.List;

public interface IStockLogisticsCompanyCodeService {

    PageData<StockLogisticsCompanyCodeVO.ListVO> pageData(StockLogisticsCompanyCodeQTO.QTO qto);

    void addStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto);

    void deleteStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto);


    void editStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto);

    StockLogisticsCompanyCodeVO.DetailVO detailStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto);

    StockLogisticsCompanyCodeVO.ListCodeCodeVO    provideLogisticsCode();
}