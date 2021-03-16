package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockKdniaoQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockKdniaoVO;

public interface IStockKdniaoService {

    PageData<StockKdniaoVO.ListVO> pageData(StockKdniaoQTO.QTO qto);

    void addStockKdniao(StockKdniaoDTO.ETO eto);

    void deleteStockKdniao(StockKdniaoDTO.IdDTO dto);


    void editStockKdniao(StockKdniaoDTO.ETO eto);

    StockKdniaoVO.DetailVO detailStockKdniao(StockKdniaoDTO.IdDTO dto);

}