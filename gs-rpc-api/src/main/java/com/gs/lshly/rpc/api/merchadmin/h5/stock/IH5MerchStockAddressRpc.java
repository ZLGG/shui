package com.gs.lshly.rpc.api.merchadmin.h5.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.dto.H5MerchStockAddressDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.qto.H5MerchStockAddressQTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.vo.H5MerchStockAddressVO;

/**
*
* @author zst
* @since 2021-01-21
*/
public interface IH5MerchStockAddressRpc {

    PageData<H5MerchStockAddressVO.ListVO> pageData(H5MerchStockAddressQTO.QTO qto);

    void addStockAddress(H5MerchStockAddressDTO.ETO eto);

    void deleteStockAddress(H5MerchStockAddressDTO.IdDTO dto);


    void editStockAddress(H5MerchStockAddressDTO.ETO eto);

    H5MerchStockAddressVO.ListVO detailStockAddress(BaseDTO dto);

}