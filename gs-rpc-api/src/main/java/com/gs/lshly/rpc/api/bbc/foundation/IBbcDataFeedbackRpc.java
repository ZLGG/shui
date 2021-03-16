package com.gs.lshly.rpc.api.bbc.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcDataFeedbackDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcDataFeedbackQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcDataFeedbackVO;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbcDataFeedbackRpc {

    PageData<BbcDataFeedbackVO.ListVO> pageData(BbcDataFeedbackQTO.QTO qto);

    void addDataFeedback(BbcDataFeedbackDTO.ETO eto);


}