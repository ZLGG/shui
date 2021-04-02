package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantSiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantSiteNavigationVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantSiteNavigationRpc;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantSiteNavigationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-08
*/
@DubboService
public class MerchantSiteNavigationRpc implements IMerchantSiteNavigationRpc{
    @Autowired
    private IMerchantSiteNavigationService  MerchantSiteNavigationService;


    @Override
    public List<MerchantSiteNavigationVO.ListVO> listSiteNavigation() {
        return MerchantSiteNavigationService.listSiteNavigation();
    }

    @Override
    public void saveMerchantSiteNavigation(List<MerchantSiteNavigationDTO.ETO> etos) {
        MerchantSiteNavigationService.saveMerchantSiteNavigation(etos);
    }
}