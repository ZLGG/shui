package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopServiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopServiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopServiceVO;

import java.util.List;

public interface IPCMerchShopServiceService {

    List<PCMerchShopServiceVO.ListVO> list(PCMerchShopServiceQTO.QTO qto);

    void addSiteCustomerService(PCMerchShopServiceDTO.ETO eto);

    List<PCMerchShopServiceVO.PhoneVO> listPhone(PCMerchShopServiceQTO.QTO qto);

    void addSiteCustomerServicePhone(PCMerchShopServiceDTO.ETOPhone dto);
}