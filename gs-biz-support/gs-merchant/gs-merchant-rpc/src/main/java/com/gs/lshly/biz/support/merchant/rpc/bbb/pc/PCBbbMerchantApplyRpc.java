package com.gs.lshly.biz.support.merchant.rpc.bbb.pc;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.PCBbbMerchantApplyDTO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IPCBbbMerchantApplyRpc;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantApplyService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-22
*/
@DubboService
public class PCBbbMerchantApplyRpc implements IPCBbbMerchantApplyRpc{

    @Autowired
    private IPCBbbMerchantApplyService  pCBbbMerchantApplyService;


    @Override
    public void addMerchantApply(PCBbbMerchantApplyDTO.ETO eto){
        pCBbbMerchantApplyService.addMerchantApply(eto);
    }


}