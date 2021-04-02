package com.gs.lshly.biz.support.merchant.rpc.bbb.pc;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantSiteNavigationVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IPCBbbMerchantSiteNavigationRpc;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantSiteNavigationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-09
*/
@DubboService
public class PCBbbMerchantSiteNavigationRpc implements IPCBbbMerchantSiteNavigationRpc{
    @Autowired
    private IPCBbbMerchantSiteNavigationService  pCBbbMerchantSiteNavigationService;


    @Override
    public List<PCBbbMerchantSiteNavigationVO.ListVO> listData() {
        return pCBbbMerchantSiteNavigationService.listData();
    }
}