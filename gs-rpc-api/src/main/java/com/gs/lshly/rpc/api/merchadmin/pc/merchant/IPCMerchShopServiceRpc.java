package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopServiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopServiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopServiceVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-09
*/
public interface IPCMerchShopServiceRpc {

    List<PCMerchShopServiceVO.ListVO> list(PCMerchShopServiceQTO.QTO qto);

    void addSiteCustomerService(PCMerchShopServiceDTO.ETO eto);
    
    List<PCMerchShopServiceVO.PhoneVO> listPhone(PCMerchShopServiceQTO.QTO qto);

    void addSiteCustomerServicPhone(PCMerchShopServiceDTO.ETOPhone dto);
}