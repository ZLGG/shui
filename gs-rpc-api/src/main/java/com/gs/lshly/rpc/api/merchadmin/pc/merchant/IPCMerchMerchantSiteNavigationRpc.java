package com.gs.lshly.rpc.api.merchadmin.pc.merchant;

import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantSiteNavigationVO;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-08
*/
public interface IPCMerchMerchantSiteNavigationRpc {

    /**
     * 商家入驻底部链接列表
     * @return
     */
    List<PCMerchMerchantSiteNavigationVO.ListVO> listData();

}