package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-18
*/
public interface IPCMerchShopNavigationRpc {

    List<PCMerchShopNavigationVO.ListVO> list(PCMerchShopNavigationQTO.QTO qto);

    List<PCMerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto);


    List<PCMerchShopNavigationVO.SimpleVO> listSimple(BaseDTO qto);

    void deleteShopNavigation(PCMerchShopNavigationDTO.DeleteDTO dto);

    void editShopNavigation(PCMerchShopNavigationDTO.ItemListDTO eto);

    void delete(PCMerchShopNavigationDTO.IdDTO dto);

    void editShopNavigationToMenu(PCMerchShopNavigationDTO.ToMenuListDTO dto);

    List<PCMerchShopNavigationVO.MenuListVO> menuList(PCMerchShopNavigationQTO.MenuQTO qto);


    List<PCMerchShopNavigationVO.InnerListVO> innerNavigationList(PCMerchShopNavigationDTO.InnerListDTO dto);

    List<PCMerchShopNavigationVO.NavigationVO> innerNavigationListWith001(String navigation001Id);
}