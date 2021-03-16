package com.gs.lshly.rpc.api.platadmin.merchant;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.PCMerchSiteVO;

/**
*
* @author Starry
* @since 2021-01-04
*/
public interface IPCMerchSiteRpc {


    /**
     * 获取商家登录配置信息
     * @param dto
     * @return
     */
    PCMerchSiteVO.LoginImageVO getLoginImageVO(BaseDTO dto);
}