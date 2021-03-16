package com.gs.lshly.rpc.api.platadmin.foundation;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface IDataFeedbackRpc {

    PageData<DataFeedbackVO.ListVO> pageData(DataFeedbackQTO.QTO qoDTO);

    void addDataFeedback(DataFeedbackDTO.ETO dto);

    void deleteDataFeedback(DataFeedbackDTO.IdListDTO dto);

    DataFeedbackVO.DetailVO getDataFeedback(DataFeedbackDTO.IdDTO dto);

    void handleDataFeedback(DataFeedbackDTO.HanderDTO dto);
}
