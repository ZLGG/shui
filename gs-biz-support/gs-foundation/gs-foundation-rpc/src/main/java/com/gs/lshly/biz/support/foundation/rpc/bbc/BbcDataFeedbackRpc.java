package com.gs.lshly.biz.support.foundation.rpc.bbc;

import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataFeedbackService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcDataFeedbackDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcDataFeedbackQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcDataFeedbackVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataFeedbackRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbcDataFeedbackRpc implements IBbcDataFeedbackRpc {

    @Autowired
    private IBbcDataFeedbackService bbcDataFeedbackService;

    @Override
    public PageData<BbcDataFeedbackVO.ListVO> pageData(BbcDataFeedbackQTO.QTO qto){
        return bbcDataFeedbackService.pageData(qto);
    }

    @Override
    public void addDataFeedback(BbcDataFeedbackDTO.ETO eto){
        bbcDataFeedbackService.addDataFeedback(eto);
    }

}