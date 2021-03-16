package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopAdvertDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopAdvertQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopAdvertVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopAdvertRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopAdvertService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-30
*/
@DubboService
public class PCMerchShopAdvertRpc implements IPCMerchShopAdvertRpc{

    @Autowired
    private IPCMerchShopAdvertService  pCMerchShopAdvertService;


    @Override
    public void editShopAdvert(PCMerchShopAdvertDTO.ETO eto){
        pCMerchShopAdvertService.editShopAdvert(eto);
    }

    @Override
    public PCMerchShopAdvertVO.DetailVO detailShopAdvert(PCMerchShopAdvertQTO.QTO qto) {
        return pCMerchShopAdvertService.detailShopAdvert(qto);
    }

}