package com.gs.lshly.rpc.api.platadmin.foundation;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeTypeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface IDataNoticeTypeRpc {

    PageData<DataNoticeTypeVO.ListVO> list(DataNoticeTypeQTO.QTO qoDTO);

    void addDataNoticeType(DataNoticeTypeDTO.ETO dto);

    void deleteDataNoticeType(DataNoticeTypeDTO.IdDTO dto);

    DataNoticeTypeVO.DetailVO getDataNoticeType(DataNoticeTypeDTO.IdDTO dto);

    void updateDataNoticeType(DataNoticeTypeDTO.EditNoticeTypeDTO dto);
}
