package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopCustomAreaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopCustomAreaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopCustomAreaVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopCustomAreaRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopCustomAreaService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-30
*/
@DubboService
public class PCMerchShopCustomAreaRpc implements IPCMerchShopCustomAreaRpc{
    @Autowired
    private IPCMerchShopCustomAreaService  pCMerchShopCustomAreaService;


    @Override
    public void editShopCustomArea(PCMerchShopCustomAreaDTO.ETO eto){
        pCMerchShopCustomAreaService.editShopCustomArea(eto);
    }

    @Override
    public PCMerchShopCustomAreaVO.DetailVO detailShopCustomArea(PCMerchShopCustomAreaQTO.QTO qto) {
        return pCMerchShopCustomAreaService.detailShopCustomArea(qto);
    }


}