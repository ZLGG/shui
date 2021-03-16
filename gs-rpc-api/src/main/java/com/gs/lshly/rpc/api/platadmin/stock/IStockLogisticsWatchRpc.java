package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsWatchDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsWatchQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsWatchVO;

/**
*
* @author zzg
* @since 2020-10-24
*/
public interface IStockLogisticsWatchRpc {

    PageData<StockLogisticsWatchVO.ListVO> pageData(StockLogisticsWatchQTO.QTO qto);//

    void addStockLogisticsWatch(StockLogisticsWatchDTO.ETO eto);//


    PageData<StockLogisticsWatchVO.ListVO> listWatch(StockLogisticsWatchQTO.QTO qto);
}