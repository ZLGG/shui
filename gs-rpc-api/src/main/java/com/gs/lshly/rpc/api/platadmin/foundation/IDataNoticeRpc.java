package com.gs.lshly.rpc.api.platadmin.foundation;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface IDataNoticeRpc {

    PageData<DataNoticeVO.ListVO> pageData(DataNoticeQTO.QTO qto);

    void addDataNotice(DataNoticeDTO.ETO dto);

    void deleteBatchDataNotice(DataNoticeDTO.IdListDTO dto);

    DataNoticeVO.DetailVO detailNotice(DataNoticeDTO.IdDTO dto);

}
