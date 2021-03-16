package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopNavigationRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopNavigationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-18
*/
@DubboService
public class PCMerchShopNavigationRpc implements IPCMerchShopNavigationRpc{

    @Autowired
    private IPCMerchShopNavigationService  pcMerchShopNavigationService;

    @Override
    public List<PCMerchShopNavigationVO.ListVO> list(PCMerchShopNavigationQTO.QTO qto){
        return pcMerchShopNavigationService.list(qto);
    }

    @Override
    public List<PCMerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto) {

        return pcMerchShopNavigationService.listLevel001(dto);
    }

    @Override
    public List<PCMerchShopNavigationVO.SimpleVO> listSimple(BaseDTO qto) {
        return pcMerchShopNavigationService.listSimple(qto);
    }

    @Override
    public void deleteShopNavigation(PCMerchShopNavigationDTO.DeleteDTO dto){
        pcMerchShopNavigationService.deleteShopNavigation(dto);
    }

    @Override
    public void editShopNavigation(PCMerchShopNavigationDTO.ItemListDTO eto){
        pcMerchShopNavigationService.editShopNavigation(eto);
    }

    @Override
    public void delete(PCMerchShopNavigationDTO.IdDTO dto) {
        pcMerchShopNavigationService.delete(dto);
    }


    @Override
    public void editShopNavigationToMenu(PCMerchShopNavigationDTO.ToMenuListDTO dto) {
        pcMerchShopNavigationService.editShopNavigationToMenu(dto);
    }

    @Override
    public List<PCMerchShopNavigationVO.MenuListVO> menuList(PCMerchShopNavigationQTO.MenuQTO qto) {
        return  pcMerchShopNavigationService.menuList(qto);
    }

    @Override
    public List<PCMerchShopNavigationVO.InnerListVO> innerNavigationList(PCMerchShopNavigationDTO.InnerListDTO dto) {
        return pcMerchShopNavigationService.innerNavigationList(dto);
    }

    @Override
    public List<PCMerchShopNavigationVO.NavigationVO> innerNavigationListWith001(String navigation001Id) {
        return pcMerchShopNavigationService.innerNavigationListWith001(navigation001Id);
    }

}