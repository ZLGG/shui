package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
public interface IDataFeedbackService {

    void delete(DataFeedbackDTO.IdListDTO dto);

    DataFeedbackVO.DetailVO detail(DataFeedbackDTO.IdDTO dto);

    PageData<DataFeedbackVO.ListVO> pageData(DataFeedbackQTO.QTO qoDTO);

    void saveDataFeedback(DataFeedbackDTO.ETO dto);

    void handleDataFeedback(DataFeedbackDTO.HanderDTO dto);

}
