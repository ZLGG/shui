package com.gs.lshly.biz.support.stock.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockMapSecretDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockMapSecretQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockMapSecretVO;

public interface IStockMapSecretService {

    PageData<StockMapSecretVO.ListVO> pageData(BaseQTO qto);

    void addStockMapSecret(StockMapSecretDTO.ETO eto);

    void deleteStockMapSecret(StockMapSecretDTO.IdDTO dto);


    void editStockMapSecret(StockMapSecretDTO.ETO eto);

    StockMapSecretVO.DetailVO detailStockMapSecret(StockMapSecretDTO.IdDTO dto);

}