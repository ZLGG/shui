package com.gs.lshly.biz.support.stock.service.bbc;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.qto.BbcStockAddressQTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;

public interface IBbcStockAddressService {

   List<BbcStockAddressVO.ListVO> list(BbcStockAddressQTO.QTO qto,Integer addressType);

    BbcStockAddressVO.DetailVO addStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType);

   void editorStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType);

   void deleteStockAddress(BbcStockAddressDTO.IdDTO dto);

    BbcStockAddressVO.DetailVO detailStockAddress(BbcStockAddressDTO.IdDTO dto);

    BbcStockAddressVO.DetailVO getDefault(BaseDTO dto,Integer addressType);

    void setDefault(BbcStockAddressDTO.IdDTO dto,Integer addressType);
    
    List<BasicAreasVO.AddressListVO> listBasicAreas(Integer pid);

}