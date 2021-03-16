package com.gs.lshly.biz.support.foundation.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchFeedbackDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchFeedbackQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchFeedbackVO;

public interface IPCMerchFeedbackService {

    PageData<PCMerchFeedbackVO.ListVO> pageData(PCMerchFeedbackQTO.QTO qto);

    void addDataFeedback(PCMerchFeedbackDTO.ETO eto);

}