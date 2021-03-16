package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockMapSecretDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockMapSecretQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockMapSecretVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockMapSecretRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockMapSecretService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-15
*/
@DubboService
public class StockMapSecretRpc implements IStockMapSecretRpc{
    @Autowired
    private IStockMapSecretService  StockMapSecretService;

    @Override
    public PageData<StockMapSecretVO.ListVO> pageData(BaseQTO qto){
        return StockMapSecretService.pageData(qto);
    }

    @Override
    public void addStockMapSecret(StockMapSecretDTO.ETO eto){
        StockMapSecretService.addStockMapSecret(eto);
    }

    @Override
    public void deleteStockMapSecret(StockMapSecretDTO.IdDTO dto){
        StockMapSecretService.deleteStockMapSecret(dto);
    }


    @Override
    public void editStockMapSecret(StockMapSecretDTO.ETO eto){
        StockMapSecretService.editStockMapSecret(eto);
    }

    @Override
    public StockMapSecretVO.DetailVO detailStockMapSecret(StockMapSecretDTO.IdDTO dto){
        return  StockMapSecretService.detailStockMapSecret(dto);
    }

}