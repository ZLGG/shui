package com.gs.lshly.biz.support.stock.rpc.bbb.h5;
import com.gs.lshly.biz.support.stock.service.bbb.h5.IBbbH5StockAddressService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.qto.BbbH5StockAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockAddressRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zzg
* @since 2020-11-02
*/
@DubboService
public class BbbH5StockAddressRpc implements IBbbH5StockAddressRpc {

    @Autowired
    private IBbbH5StockAddressService stockAddressService;



    @Override
    public BbbH5StockAddressVO.DetailVO saveStockAddress(BbbH5StockAddressDTO.DetailETO eto) {

       return stockAddressService.saveStockAddress(eto);
    }

    @Override
    public void deleteAddress(BbbH5StockAddressDTO.IdDTO dto) {
        stockAddressService.deleteAddress(dto);
    }

    @Override
    public BbbH5StockAddressVO.DetailVO detailStockAddress(BbbH5StockAddressDTO.IdAndTypeDTO dto) {
        return stockAddressService.detailStockAddress(dto);
    }

    @Override
    public BbbH5StockAddressVO.DetailVO innerdetailStockAddress(BbbH5StockAddressDTO.IdAndTypeDTO dto) {

        return stockAddressService.innerdetailStockAddress(dto);
    }

    @Override
    public PageData<BbbH5StockAddressVO.ListVO> pageAddressListVO(BbbH5StockAddressQTO.QTO qto) {
        return stockAddressService.pageAddressListVO(qto);
    }

    @Override
    public BbbH5StockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType) {
        return stockAddressService.innerGetDefault(dto, addressType);
    }


}