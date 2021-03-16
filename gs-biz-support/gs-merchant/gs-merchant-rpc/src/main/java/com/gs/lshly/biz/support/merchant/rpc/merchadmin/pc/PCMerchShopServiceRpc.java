package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopServiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopServiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopServiceVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopServiceRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopServiceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-09
*/
@DubboService
public class PCMerchShopServiceRpc implements IPCMerchShopServiceRpc {
    @Autowired
    private IPCMerchShopServiceService pcMerchShopServiceService;


    @Override
    public List<PCMerchShopServiceVO.ListVO> list(PCMerchShopServiceQTO.QTO qto) {
        return pcMerchShopServiceService.list(qto);
    }

    @Override
    public void addSiteCustomerService(PCMerchShopServiceDTO.ETO eto){
        pcMerchShopServiceService.addSiteCustomerService(eto);
    }

    @Override
    public List<PCMerchShopServiceVO.PhoneVO> listPhone(PCMerchShopServiceQTO.QTO qto) {
        return pcMerchShopServiceService.listPhone(qto);
    }

    @Override
    public void addSiteCustomerServicPhone(PCMerchShopServiceDTO.ETOPhone dto) {
        pcMerchShopServiceService.addSiteCustomerServicePhone(dto);
    }


}