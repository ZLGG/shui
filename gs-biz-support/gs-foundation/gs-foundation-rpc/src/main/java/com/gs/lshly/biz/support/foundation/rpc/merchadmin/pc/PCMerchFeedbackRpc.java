package com.gs.lshly.biz.support.foundation.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchFeedbackService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchFeedbackDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchFeedbackQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchFeedbackVO;
import com.gs.lshly.rpc.api.merchadmin.pc.foundation.IPCMerchFeedbackRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class PCMerchFeedbackRpc implements IPCMerchFeedbackRpc {


    @Autowired
    private IPCMerchFeedbackService pCMerchFeedbackService;

    @Override
    public PageData<PCMerchFeedbackVO.ListVO> pageData(PCMerchFeedbackQTO.QTO qto) {
        return pCMerchFeedbackService.pageData(qto);
    }

    @Override
    public void addDataFeedback(PCMerchFeedbackDTO.ETO eto) {
        pCMerchFeedbackService.addDataFeedback(eto);
    }
}