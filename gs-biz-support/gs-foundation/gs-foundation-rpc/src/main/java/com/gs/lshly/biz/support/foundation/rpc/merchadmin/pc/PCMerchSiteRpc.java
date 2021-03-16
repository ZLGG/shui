package com.gs.lshly.biz.support.foundation.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchSiteService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.PCMerchSiteVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IPCMerchSiteRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2021-01-04
*/
@DubboService
public class PCMerchSiteRpc implements IPCMerchSiteRpc {
    @Autowired
    private IPCMerchSiteService pCMerchSiteService;


    @Override
    public PCMerchSiteVO.LoginImageVO getLoginImageVO(BaseDTO dto) {
        return pCMerchSiteService.getLoginImageVO(dto);
    }
}