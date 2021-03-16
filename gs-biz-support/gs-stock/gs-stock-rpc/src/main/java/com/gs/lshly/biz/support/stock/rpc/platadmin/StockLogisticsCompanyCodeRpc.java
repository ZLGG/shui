package com.gs.lshly.biz.support.stock.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCompanyCodeDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCompanyCodeQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCompanyCodeVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockLogisticsCompanyCodeRpc;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsCompanyCodeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zzg
* @since 2020-10-30
*/
@DubboService
public class StockLogisticsCompanyCodeRpc implements IStockLogisticsCompanyCodeRpc{
    @Autowired
    private IStockLogisticsCompanyCodeService  StockLogisticsCompanyCodeService;

    @Override
    public PageData<StockLogisticsCompanyCodeVO.ListVO> pageData(StockLogisticsCompanyCodeQTO.QTO qto){
        return StockLogisticsCompanyCodeService.pageData(qto);
    }

    @Override
    public void addStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto){
        StockLogisticsCompanyCodeService.addStockLogisticsCompanyCode(eto);
    }

    @Override
    public void deleteStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto){
        StockLogisticsCompanyCodeService.deleteStockLogisticsCompanyCode(dto);
    }


    @Override
    public void editStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto){
        StockLogisticsCompanyCodeService.editStockLogisticsCompanyCode(eto);
    }

    @Override
    public StockLogisticsCompanyCodeVO.DetailVO detailStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto){
        return  StockLogisticsCompanyCodeService.detailStockLogisticsCompanyCode(dto);
    }


    /*@Override
    public StockLogisticsCompanyCodeVO.ListVO provideLogisticsCode() {
        StockLogisticsCompanyCodeVO.ListVO listVO = StockLogisticsCompanyCodeService.provideLogisticsCode();
        return listVO;
    }*/

    @Override
    public StockLogisticsCompanyCodeVO.ListCodeCodeVO provideLogisticsCode() {
        StockLogisticsCompanyCodeVO.ListCodeCodeVO listCodeCodeVO = StockLogisticsCompanyCodeService.provideLogisticsCode();
        return listCodeCodeVO;
    }


}