package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataFeedbackDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataFeedbackQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataFeedbackVO;
/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbbH5DataFeedbackRpc {

    PageData<BbbH5DataFeedbackVO.ListVO> pageData(BbbH5DataFeedbackQTO.QTO qto);

    void addDataFeedback(BbbH5DataFeedbackDTO.ETO eto);


}