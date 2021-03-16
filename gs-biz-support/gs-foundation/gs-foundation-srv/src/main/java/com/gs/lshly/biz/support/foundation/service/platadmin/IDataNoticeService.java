package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
public interface IDataNoticeService {


    PageData<DataNoticeVO.ListVO> pageData(DataNoticeQTO.QTO qoDTO);

    void deleteBatchDataNotice(DataNoticeDTO.IdListDTO dto);

    DataNoticeVO.DetailVO detail(DataNoticeDTO.IdDTO dto);


    void addDataNotice(DataNoticeDTO.ETO dto);

}
