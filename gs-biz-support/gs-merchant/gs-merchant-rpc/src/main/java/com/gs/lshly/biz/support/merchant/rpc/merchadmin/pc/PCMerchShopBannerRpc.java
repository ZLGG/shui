package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopBannerDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopBannerQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopBannerVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopBannerRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopBannerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-23
*/
@DubboService
public class PCMerchShopBannerRpc implements IPCMerchShopBannerRpc{

    @Autowired
    private IPCMerchShopBannerService  pCMerchShopBannerService;


    @Override
    public PageData<PCMerchShopBannerVO.ListVO> pageData(PCMerchShopBannerQTO.QTO qto) {
        return null;
    }

    @Override
    public List<PCMerchShopBannerVO.H5ListVO> h5List(PCMerchShopBannerQTO.H5QTO qto) {
        return pCMerchShopBannerService.h5List(qto);
    }

    @Override
    public void h5Editor(PCMerchShopBannerDTO.H5ETO eto) {
        pCMerchShopBannerService.h5Editor(eto);
    }

    @Override
    public List<PCMerchShopBannerVO.PCListVO> pcList(PCMerchShopBannerQTO.PCQTO qto) {
        return pCMerchShopBannerService.pcList(qto);
    }

    @Override
    public void pcEditor(PCMerchShopBannerDTO.PCETO eto) {
        pCMerchShopBannerService.pcEditor(eto);
    }
}