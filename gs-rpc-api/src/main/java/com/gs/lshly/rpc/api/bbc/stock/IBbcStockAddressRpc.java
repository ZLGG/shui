package com.gs.lshly.rpc.api.bbc.stock;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.qto.BbcStockAddressQTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO.ListBasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

/**
*会员地址管理
* @author zzg
* @since 2020-11-02
*/
public interface IBbcStockAddressRpc {

    List<BbcStockAddressVO.ListVO> list(BbcStockAddressQTO.QTO qto,Integer addressType);

    BbcStockAddressVO.DetailVO addStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType);

    void editorStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType);

    void deleteStockAddress(BbcStockAddressDTO.IdDTO dto);

    BbcStockAddressVO.DetailVO detailStockAddress(BbcStockAddressDTO.IdDTO dto);

    BbcStockAddressVO.DetailVO getDefault(BaseDTO dto,Integer addressType);

    void setDefault(BbcStockAddressDTO.IdDTO dto,Integer addressType);

    /**
     * 默认地址 addressType[10=收货 20=发票]
     * @param dto
     * @param addressType
     * @return
     */
    BbcStockAddressVO.DetailVO innerGetDefault(BaseDTO dto,Integer addressType);
    
    List<BasicAreasVO.AddressListVO> listBasicAreas(Integer pid);

}