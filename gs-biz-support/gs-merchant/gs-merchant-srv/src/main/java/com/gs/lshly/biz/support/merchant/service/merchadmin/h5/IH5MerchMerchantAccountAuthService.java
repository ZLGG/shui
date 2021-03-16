package com.gs.lshly.biz.support.merchant.service.merchadmin.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;

public interface IH5MerchMerchantAccountAuthService {

    H5MerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto);
}