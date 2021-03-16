package com.gs.lshly.rpc.api.merchadmin.h5.merchant;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;

/**
 * @author zst
 */
public interface IH5MerchMerchantAccountAuthRpc {

    H5MerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto);

}
