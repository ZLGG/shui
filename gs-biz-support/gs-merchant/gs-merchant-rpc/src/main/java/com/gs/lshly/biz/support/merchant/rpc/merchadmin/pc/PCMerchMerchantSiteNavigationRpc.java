package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantSiteNavigationVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantSiteNavigationRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantSiteNavigationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-08
*/
@DubboService
public class PCMerchMerchantSiteNavigationRpc implements IPCMerchMerchantSiteNavigationRpc{
    @Autowired
    private IPCMerchMerchantSiteNavigationService  pCMerchMerchantSiteNavigationService;

    @Override
    public List<PCMerchMerchantSiteNavigationVO.ListVO> listData() {
        return pCMerchMerchantSiteNavigationService.listData();
    }
}