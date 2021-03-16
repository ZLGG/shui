package com.gs.lshly.biz.support.foundation.rpc.platadmin;

import com.gs.lshly.biz.support.foundation.service.platadmin.IDataNoticeTypeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeTypeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataNoticeTypeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RPC里面不允许写业务代码：
 * 只允许做三件事情：
 * 1，面向Service做数据准备；
 * 2，调用Service；
 * 3，面向前端解析VO数据；
 *
 * @author lxus
 * @since 2020/09/14
 */
@DubboService
public class DataNoticeTypeRpc implements IDataNoticeTypeRpc {

    @Autowired
    private IDataNoticeTypeService dataNoticeTypeService;


    @Override
    public PageData<DataNoticeTypeVO.ListVO> list(DataNoticeTypeQTO.QTO qoDTO) {
        return dataNoticeTypeService.pageData(qoDTO);
    }

    @Override
    public void addDataNoticeType(DataNoticeTypeDTO.ETO dto) {
        dataNoticeTypeService.saveDataNoticeType(dto);
    }

    @Override
    public void deleteDataNoticeType(DataNoticeTypeDTO.IdDTO dto) {
        dataNoticeTypeService.delete(dto);
    }

    @Override
    public DataNoticeTypeVO.DetailVO getDataNoticeType(DataNoticeTypeDTO.IdDTO dto) {
        return dataNoticeTypeService.detail(dto);
    }

    @Override
    public void updateDataNoticeType(DataNoticeTypeDTO.EditNoticeTypeDTO dto) {
        dataNoticeTypeService.editDataNoticeType(dto);
    }
}
