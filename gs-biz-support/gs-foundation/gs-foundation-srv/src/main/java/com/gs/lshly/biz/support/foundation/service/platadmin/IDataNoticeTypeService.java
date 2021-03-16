package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeTypeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
public interface IDataNoticeTypeService {

    void delete(DataNoticeTypeDTO.IdDTO dto);

    DataNoticeTypeVO.DetailVO detail(DataNoticeTypeDTO.IdDTO dto);

    PageData<DataNoticeTypeVO.ListVO> pageData(DataNoticeTypeQTO.QTO qoDTO);

    void saveDataNoticeType(DataNoticeTypeDTO.ETO dto);

    void editDataNoticeType(DataNoticeTypeDTO.EditNoticeTypeDTO dto);

}
