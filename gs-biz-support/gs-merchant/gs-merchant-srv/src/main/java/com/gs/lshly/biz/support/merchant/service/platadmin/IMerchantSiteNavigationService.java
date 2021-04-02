package com.gs.lshly.biz.support.merchant.service.platadmin;

import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantSiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantSiteNavigationVO;

import java.util.List;

public interface IMerchantSiteNavigationService {


    /**
     * 商家入驻底部链接列表
     * @return
     */
    List<MerchantSiteNavigationVO.ListVO> listSiteNavigation();

    /**
     * 保存商家入驻底部链接
     * @param etos
     */
    void saveMerchantSiteNavigation(List<MerchantSiteNavigationDTO.ETO> etos);
}