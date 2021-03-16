package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;

import java.util.List;

public interface IPCMerchShopNavigationService {

    List<PCMerchShopNavigationVO.ListVO> list(PCMerchShopNavigationQTO.QTO qto);

    List<PCMerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto);

    List<PCMerchShopNavigationVO.SimpleVO> listSimple(BaseDTO qto);

    void deleteShopNavigation(PCMerchShopNavigationDTO.DeleteDTO dto);

    void delete(PCMerchShopNavigationDTO.IdDTO dto);

    void editShopNavigation(PCMerchShopNavigationDTO.ItemListDTO eto);

    void editShopNavigationToMenu(PCMerchShopNavigationDTO.ToMenuListDTO dto);

    List<PCMerchShopNavigationVO.MenuListVO> menuList(PCMerchShopNavigationQTO.MenuQTO qto);


    List<PCMerchShopNavigationVO.InnerListVO> innerNavigationList(PCMerchShopNavigationDTO.InnerListDTO dto);

    List<PCMerchShopNavigationVO.NavigationVO> innerNavigationListWith001(String navigation001Id);
}