package com.gs.lshly.rpc.api.bbb.pc.merchant;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;

/**
*
* @author xxfc
* @since 2020-10-08
*/
public interface IBbbMerchantAccountRpc {

    MerchantAccountVO.MerchShopVO getMerchantShopByPhone(MerchantAccountQTO.PhoneQTO qto);

}