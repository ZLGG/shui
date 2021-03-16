package com.gs.lshly.biz.support.stock.rpc.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.dto.H5MerchStockAddressDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.qto.H5MerchStockAddressQTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.vo.H5MerchStockAddressVO;
import com.gs.lshly.rpc.api.merchadmin.h5.stock.IH5MerchStockAddressRpc;
import com.gs.lshly.biz.support.stock.service.merchadmin.h5.IH5MerchStockAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2021-01-21
*/
@DubboService
public class H5MerchStockAddressRpc implements IH5MerchStockAddressRpc{
    @Autowired
    private IH5MerchStockAddressService  h5MerchStockAddressService;

    @Override
    public PageData<H5MerchStockAddressVO.ListVO> pageData(H5MerchStockAddressQTO.QTO qto){
        return h5MerchStockAddressService.pageData(qto);
    }

    @Override
    public void addStockAddress(H5MerchStockAddressDTO.ETO eto){
        h5MerchStockAddressService.addStockAddress(eto);
    }

    @Override
    public void deleteStockAddress(H5MerchStockAddressDTO.IdDTO dto){
        h5MerchStockAddressService.deleteStockAddress(dto);
    }


    @Override
    public void editStockAddress(H5MerchStockAddressDTO.ETO eto){
        h5MerchStockAddressService.editStockAddress(eto);
    }

    @Override
    public H5MerchStockAddressVO.ListVO detailStockAddress(BaseDTO dto){
        return  h5MerchStockAddressService.detailStockAddress(dto);
    }

}