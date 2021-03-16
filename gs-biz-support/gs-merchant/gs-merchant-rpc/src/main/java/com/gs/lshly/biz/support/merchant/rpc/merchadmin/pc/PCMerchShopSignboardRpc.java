package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopSignboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopSignboardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopSignboardVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopSignboardRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopSignboardService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-30
*/
@DubboService
public class PCMerchShopSignboardRpc implements IPCMerchShopSignboardRpc{
    @Autowired
    private IPCMerchShopSignboardService  pCMerchShopSignboardService;


    @Override
    public PCMerchShopSignboardVO.DetailVO detailSignboard(PCMerchShopSignboardQTO.QTO qto) {
        return pCMerchShopSignboardService.detailSignboard(qto);
    }

    @Override
    public void editShopSignboard(PCMerchShopSignboardDTO.ETO eto) {
        pCMerchShopSignboardService.editShopSignboard(eto);
    }
}