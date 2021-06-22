package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.stock.service.common.IPCMerchTemplateService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;
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

    @Override
    public TemplateVO.DetailVO detail(String id) {
        return pCMerchTemplateService.detail(id);
    }

    @Override
    public Boolean delete(String id) {
        return pCMerchTemplateService.delete(id);
    }

    @Override
    public PageData<TemplateVO.ListVO> list(TemplateQTO.QTO qto) {
        return pCMerchTemplateService.list(qto);
    }
}
