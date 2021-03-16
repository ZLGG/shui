package com.gs.lshly.biz.support.foundation.rpc.platadmin;

import com.gs.lshly.biz.support.foundation.service.platadmin.IDataNoticeService;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataNoticeTypeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataNoticeRpc;
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
public class DataNoticeRpc implements IDataNoticeRpc {

    @Autowired
    private IDataNoticeService dataNoticeService;

    @Override
    public PageData<DataNoticeVO.ListVO> pageData(DataNoticeQTO.QTO qto) {

        return dataNoticeService.pageData(qto);
    }

    @Override
    public void addDataNotice(DataNoticeDTO.ETO dto) {

        dataNoticeService.addDataNotice(dto);
    }

    @Override
    public void deleteBatchDataNotice(DataNoticeDTO.IdListDTO dto) {
        dataNoticeService.deleteBatchDataNotice(dto);
    }

    @Override
    public DataNoticeVO.DetailVO detailNotice(DataNoticeDTO.IdDTO dto) {
        return dataNoticeService.detail(dto);
    }


}
