package com.gs.lshly.rpc.api.platadmin.merchant;

import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantSiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantSiteNavigationVO;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-08
*/
public interface IMerchantSiteNavigationRpc {

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