package com.gs.lshly.biz.support.merchant.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchMerchantAccountAuthService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchMerchantAccountAuthRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zst
 */
@DubboService
public class H5MerchMerchantAccountAuthRpc implements IH5MerchMerchantAccountAuthRpc {

    @Autowired
    private IH5MerchMerchantAccountAuthService accountService;

    @Override
    public H5MerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto) {
        return accountService.changeShop(shopId,dto);
    }


}
