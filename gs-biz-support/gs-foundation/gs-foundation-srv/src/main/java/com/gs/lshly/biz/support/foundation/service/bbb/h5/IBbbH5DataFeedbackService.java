package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataFeedbackDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataFeedbackQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataFeedbackVO;

public interface IBbbH5DataFeedbackService {

    PageData<BbbH5DataFeedbackVO.ListVO> pageData(BbbH5DataFeedbackQTO.QTO qto);

    void addDataFeedback(BbbH5DataFeedbackDTO.ETO eto);

}