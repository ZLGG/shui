package com.gs.lshly.rpc.api.platadmin.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockMapSecretDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockMapSecretQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockMapSecretVO;

/**
*
* @author zst
* @since 2021-01-15
*/
public interface IStockMapSecretRpc {

    PageData<StockMapSecretVO.ListVO> pageData(BaseQTO qto);

    void addStockMapSecret(StockMapSecretDTO.ETO eto);

    void deleteStockMapSecret(StockMapSecretDTO.IdDTO dto);


    void editStockMapSecret(StockMapSecretDTO.ETO eto);

    StockMapSecretVO.DetailVO detailStockMapSecret(StockMapSecretDTO.IdDTO dto);

}