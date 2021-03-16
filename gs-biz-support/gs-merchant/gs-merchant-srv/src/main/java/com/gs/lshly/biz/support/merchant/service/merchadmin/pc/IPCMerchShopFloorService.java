package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopFloorQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;

import java.util.List;

public interface IPCMerchShopFloorService {

    List<PCMerchShopFloorVO.H5ListVO> h5List(PCMerchShopFloorQTO.H5QTO qto);

    void h5Editor(PCMerchShopFloorDTO.H5ETO eto);

    List<PCMerchShopFloorVO.PCListVO> pcList(PCMerchShopFloorQTO.PCQTO qto);

    void pcEditor(PCMerchShopFloorDTO.PCETO eto);

}