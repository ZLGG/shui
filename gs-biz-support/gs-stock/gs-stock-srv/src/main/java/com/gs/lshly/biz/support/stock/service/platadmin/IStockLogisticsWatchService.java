package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsWatchDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsWatchQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsWatchVO;

public interface IStockLogisticsWatchService {

    PageData<StockLogisticsWatchVO.ListVO> pageData(StockLogisticsWatchQTO.QTO qto);

    void addStockLogisticsWatch(StockLogisticsWatchDTO.ETO eto);


    PageData<StockLogisticsWatchVO.ListVO> listWatch(StockLogisticsWatchQTO.QTO qto);
}