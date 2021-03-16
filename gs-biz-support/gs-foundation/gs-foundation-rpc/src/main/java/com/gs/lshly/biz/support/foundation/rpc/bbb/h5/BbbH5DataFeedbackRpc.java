package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataFeedbackService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataFeedbackDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataFeedbackQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataFeedbackVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataFeedbackRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbbH5DataFeedbackRpc implements IBbbH5DataFeedbackRpc {

    @Autowired
    private IBbbH5DataFeedbackService bbbH5DataFeedbackService;

    @Override
    public PageData<BbbH5DataFeedbackVO.ListVO> pageData(BbbH5DataFeedbackQTO.QTO qto){
        return bbbH5DataFeedbackService.pageData(qto);
    }

    @Override
    public void addDataFeedback(BbbH5DataFeedbackDTO.ETO eto){
        bbbH5DataFeedbackService.addDataFeedback(eto);
    }

}