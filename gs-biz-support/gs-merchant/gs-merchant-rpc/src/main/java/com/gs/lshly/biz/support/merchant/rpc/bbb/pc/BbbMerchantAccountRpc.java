package com.gs.lshly.biz.support.merchant.rpc.bbb.pc;

import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAccountService;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbMerchantAccountRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-08
*/
@DubboService
public class BbbMerchantAccountRpc implements IBbbMerchantAccountRpc {

    @Autowired
    private IMerchantAccountService merchantAccountService;


    /**
     * 通过手机号查找店铺ID
     * @param qto
     * @return
     */
    @Override
    public MerchantAccountVO.MerchShopVO getMerchantShopByPhone(MerchantAccountQTO.PhoneQTO qto) {
        return  merchantAccountService.getMerchantShopByPhone(qto);
    }

}