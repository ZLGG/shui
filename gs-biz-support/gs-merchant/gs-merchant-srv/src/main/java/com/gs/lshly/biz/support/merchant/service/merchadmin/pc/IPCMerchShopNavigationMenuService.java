package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationMenuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationMenuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationMenuVO;

import java.util.List;

public interface IPCMerchShopNavigationMenuService {

    List<PCMerchShopNavigationMenuVO.H5ListVO> h5List(PCMerchShopNavigationMenuQTO.H5QTO qto);

    void h5Editor(PCMerchShopNavigationMenuDTO.H5ETO eto);

    List<PCMerchShopNavigationMenuVO.H5TextMenuListVO> h5TextMenuList(PCMerchShopNavigationMenuQTO.H5TextMenuQTO qto);

    void h5TextMenuEditor(PCMerchShopNavigationMenuDTO.H5TextMenuETO eto);


    PCMerchShopNavigationMenuVO.PcShopMenuVO pcShopMenuList(PCMerchShopNavigationMenuQTO.PCShopMenuQTO qto);

    void pcShopMenuEditor(PCMerchShopNavigationMenuDTO.PCShopMenuETO eto);

}