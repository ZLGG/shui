package com.gs.lshly.biz.support.stock.rpc.bbb.pc;

import com.gs.lshly.biz.support.stock.service.bbb.pc.IBbbPcStockAddressService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.qto.BbbStockAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zzg
* @since 2020-10-24c
*/
@DubboService
public class BbbPcStockAddressRpc implements IBbbPcStockAddressRpc {

    @Autowired
    private IBbbPcStockAddressService IBbbPcStockAddressService;


    @Override
    public String queryStockAddress(String id) {
        return IBbbPcStockAddressService.queryStockAddress(id);
    }

    @Override
    public BbbStockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType) {
        return IBbbPcStockAddressService.getDefault(dto,addressType);
    }



    @Override
    public void saveStockAddress(BbbStockAddressDTO.DetailETO eto) {
        IBbbPcStockAddressService.saveStockAddress(eto);
    }

    @Override
    public PageData<BbbStockAddressVO.ListVO> pageAddressListVO(BbbStockAddressQTO.QTO qto) {
        return IBbbPcStockAddressService.pageAddressListVO(qto);
    }

    @Override
    public void setDefaultStockAddress(BbbStockAddressDTO.SetDefaultDTO dto) {
        IBbbPcStockAddressService.setDefaultStockAddress(dto);
    }

    @Override
    public void deleteStockAddress(BbbStockAddressDTO.IdDTO dto) {
        IBbbPcStockAddressService.deleteStockAddress(dto);
    }

    @Override
    public BbbStockAddressVO.ListVO detailStockAddress(BbbStockAddressDTO.EditDTO dto) {
        return IBbbPcStockAddressService.detailStockAddress(dto);
    }

    @Override
    public List<BbbStockAddressVO.ListVO> listStockAddressVO(BbbStockAddressDTO.AddressTypeDTO dto) {
        return IBbbPcStockAddressService.listStockAddressVO(dto);
    }

    @Override
    public BbbStockAddressVO.ListVO innerDetailVO(BbbStockAddressDTO.IdDTO dto) {
        return IBbbPcStockAddressService.innerDetailVO(dto);
    }

    @Override
    public BbbStockAddressVO.DetailVO getDefault(BaseDTO dto, Integer addressType) {
        return IBbbPcStockAddressService.getDefault(dto, addressType);
    }


}