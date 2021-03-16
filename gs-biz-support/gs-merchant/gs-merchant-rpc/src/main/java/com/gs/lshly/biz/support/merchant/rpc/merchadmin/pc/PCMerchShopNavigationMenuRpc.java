package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationMenuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationMenuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationMenuVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopNavigationMenuRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopNavigationMenuService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-04
*/
@DubboService
public class PCMerchShopNavigationMenuRpc implements IPCMerchShopNavigationMenuRpc{

    @Autowired
    private IPCMerchShopNavigationMenuService  pCMerchShopNavigationMenuService;


    @Override
    public List<PCMerchShopNavigationMenuVO.H5ListVO> h5List(PCMerchShopNavigationMenuQTO.H5QTO qto) {
        return pCMerchShopNavigationMenuService.h5List(qto);
    }

    @Override
    public void h5Editor(PCMerchShopNavigationMenuDTO.H5ETO eto) {
        pCMerchShopNavigationMenuService.h5Editor(eto);
    }

    @Override
    public List<PCMerchShopNavigationMenuVO.H5TextMenuListVO> h5TextMenuList(PCMerchShopNavigationMenuQTO.H5TextMenuQTO qto) {
        return pCMerchShopNavigationMenuService.h5TextMenuList(qto);
    }

    @Override
    public void h5TextMenuEditor(PCMerchShopNavigationMenuDTO.H5TextMenuETO eto) {
        pCMerchShopNavigationMenuService.h5TextMenuEditor(eto);
    }

    @Override
    public PCMerchShopNavigationMenuVO.PcShopMenuVO pcShopMenuList(PCMerchShopNavigationMenuQTO.PCShopMenuQTO qto) {
        return pCMerchShopNavigationMenuService.pcShopMenuList(qto);
    }

    @Override
    public void pcShopMenuEditor(PCMerchShopNavigationMenuDTO.PCShopMenuETO eto) {
        pCMerchShopNavigationMenuService.pcShopMenuEditor(eto);
    }
}