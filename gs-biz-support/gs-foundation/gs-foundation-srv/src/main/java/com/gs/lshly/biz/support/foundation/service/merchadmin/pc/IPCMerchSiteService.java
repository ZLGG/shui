package com.gs.lshly.biz.support.foundation.service.merchadmin.pc;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.PCMerchSiteVO;

public interface IPCMerchSiteService {

    /**
     * 获取商家登录配置信息
     * @param dto
     * @return
     */
    PCMerchSiteVO.LoginImageVO getLoginImageVO(BaseDTO dto);

}