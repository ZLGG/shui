package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantSiteNavigationVO;

import java.util.List;

public interface IPCMerchMerchantSiteNavigationService {

    /**
     * 商家入驻底部链接列表
     * @return
     */
    List<PCMerchMerchantSiteNavigationVO.ListVO> listData();


}