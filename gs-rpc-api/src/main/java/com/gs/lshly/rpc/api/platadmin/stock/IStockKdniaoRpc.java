package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockKdniaoQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockKdniaoVO;

/**
*
* @author zst
* @since 2021-01-29
*/
public interface IStockKdniaoRpc {

    PageData<StockKdniaoVO.ListVO> pageData(StockKdniaoQTO.QTO qto);

    void addStockKdniao(StockKdniaoDTO.ETO eto);

    void deleteStockKdniao(StockKdniaoDTO.IdDTO dto);


    void editStockKdniao(StockKdniaoDTO.ETO eto);

    StockKdniaoVO.DetailVO detailStockKdniao(StockKdniaoDTO.IdDTO dto);

}