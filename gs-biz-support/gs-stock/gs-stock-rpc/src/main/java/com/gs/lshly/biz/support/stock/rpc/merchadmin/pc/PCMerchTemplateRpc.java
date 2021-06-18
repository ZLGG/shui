package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchTemplateService;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchTemplateRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenyang
 */
@DubboService
public class PCMerchTemplateRpc implements IPCMerchTemplateRpc {

    @Autowired
    private IPCMerchTemplateService pCMerchTemplateService;

    @Override
    public Boolean addTemplate(CommonTemplateDTO.AddETO eto) {
        return pCMerchTemplateService.addTemplate(eto);
    }

    @Override
    public Boolean editTemplate(CommonTemplateDTO.UpdateETO eto) {
        return pCMerchTemplateService.editTemplate(eto);
    }
}
