package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopFloorQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-05
*/
public interface IPCMerchShopFloorRpc {

    List<PCMerchShopFloorVO.H5ListVO> h5List(PCMerchShopFloorQTO.H5QTO qto);

    void h5Editor(PCMerchShopFloorDTO.H5ETO eto);

    List<PCMerchShopFloorVO.PCListVO> pcList(PCMerchShopFloorQTO.PCQTO qto);

    void pcEditor(PCMerchShopFloorDTO.PCETO eto);
}