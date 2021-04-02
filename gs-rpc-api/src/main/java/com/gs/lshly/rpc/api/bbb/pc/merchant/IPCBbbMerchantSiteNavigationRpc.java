package com.gs.lshly.rpc.api.bbb.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantSiteNavigationVO;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-09
*/
public interface IPCBbbMerchantSiteNavigationRpc {

    List<PCBbbMerchantSiteNavigationVO.ListVO> listData();
}