package com.gs.lshly.biz.support.merchant.rpc.bbb.pc;

import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantUserTypeService;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IPCBbbMerchantUserTypeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-12-25
*/
@DubboService
public class PCBbbMerchantUserTypeRpc implements IPCBbbMerchantUserTypeRpc {

    @Autowired
    private IPCBbbMerchantUserTypeService pCBbbMerchantUserTypeService;


    @Override
    public PCBbbMerchantUserTypeVO.DetailsVO details(String id) {
        return pCBbbMerchantUserTypeService.details(id);
    }
}