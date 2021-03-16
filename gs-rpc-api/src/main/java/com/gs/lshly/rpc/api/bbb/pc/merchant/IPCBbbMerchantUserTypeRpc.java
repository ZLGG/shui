package com.gs.lshly.rpc.api.bbb.pc.merchant;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;

/**
*
* @author xxfc
* @since 2020-12-25
*/
public interface IPCBbbMerchantUserTypeRpc {

    PCBbbMerchantUserTypeVO.DetailsVO details(String id);

}