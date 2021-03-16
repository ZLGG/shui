package com.gs.lshly.biz.support.foundation.service.bbc;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcDataFeedbackDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcDataFeedbackQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcDataFeedbackVO;

public interface IBbcDataFeedbackService {

    PageData<BbcDataFeedbackVO.ListVO> pageData(BbcDataFeedbackQTO.QTO qto);

    void addDataFeedback(BbcDataFeedbackDTO.ETO eto);

}