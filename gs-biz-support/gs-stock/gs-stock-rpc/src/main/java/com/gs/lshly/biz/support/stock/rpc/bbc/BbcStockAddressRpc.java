package com.gs.lshly.biz.support.stock.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.qto.BbcStockAddressQTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockAddressRpc;
import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zzg
* @since 2020-11-02
*/
@DubboService
public class BbcStockAddressRpc implements IBbcStockAddressRpc{

    @Autowired
    private IBbcStockAddressService  bbcStockAddressService;


    @Override
    public List<BbcStockAddressVO.ListVO> list(BbcStockAddressQTO.QTO qto,Integer addressType) {


        return bbcStockAddressService.list(qto,addressType);
    }

    @Override
    public BbcStockAddressVO.DetailVO addStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType) {

       return bbcStockAddressService.addStockAddress(eto,addressType);
    }

    @Override
    public void editorStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType) {
        bbcStockAddressService.editorStockAddress(eto,addressType);
    }

    @Override
    public void deleteStockAddress(BbcStockAddressDTO.IdDTO dto) {
        bbcStockAddressService.deleteStockAddress(dto);
    }

    @Override
    public BbcStockAddressVO.DetailVO detailStockAddress(BbcStockAddressDTO.IdDTO dto) {
        return bbcStockAddressService.detailStockAddress(dto);
    }

    @Override
    public BbcStockAddressVO.DetailVO getDefault(BaseDTO dto,Integer addressType) {

        return bbcStockAddressService.getDefault(dto,addressType);
    }

    @Override
    public void setDefault(BbcStockAddressDTO.IdDTO dto,Integer addressType) {
        bbcStockAddressService.setDefault(dto,addressType);
    }

    @Override
    public BbcStockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType) {
       return bbcStockAddressService.getDefault(dto,addressType);
    }

}