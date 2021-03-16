package com.gs.lshly.rpc.api.merchadmin.pc.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchFeedbackDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchFeedbackQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchFeedbackVO;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IPCMerchFeedbackRpc {

    PageData<PCMerchFeedbackVO.ListVO> pageData(PCMerchFeedbackQTO.QTO qto);

    void addDataFeedback(PCMerchFeedbackDTO.ETO eto);


}