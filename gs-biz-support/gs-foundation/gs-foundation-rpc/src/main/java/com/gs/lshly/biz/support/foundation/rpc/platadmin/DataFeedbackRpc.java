package com.gs.lshly.biz.support.foundation.rpc.platadmin;

import com.gs.lshly.biz.support.foundation.service.platadmin.IDataFeedbackService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataFeedbackRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 * @since 2020/09/14
 */
@DubboService
public class DataFeedbackRpc implements IDataFeedbackRpc {

    @Autowired
    private IDataFeedbackService dataFeedbackService;


    @Override
    public PageData<DataFeedbackVO.ListVO> pageData(DataFeedbackQTO.QTO qoDTO) {
        return dataFeedbackService.pageData(qoDTO);
    }

    @Override
    public void addDataFeedback(DataFeedbackDTO.ETO dto) {
        dataFeedbackService.saveDataFeedback(dto);
    }

    @Override
    public void deleteDataFeedback(DataFeedbackDTO.IdListDTO dto) {
        dataFeedbackService.delete(dto);
    }

    @Override
    public DataFeedbackVO.DetailVO getDataFeedback(DataFeedbackDTO.IdDTO dto) {

        return dataFeedbackService.detail(dto);
    }

    @Override
    public void handleDataFeedback(DataFeedbackDTO.HanderDTO dto) {
        dataFeedbackService.handleDataFeedback(dto);

    }
}
