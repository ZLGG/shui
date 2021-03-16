package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysDataMessageDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
public interface ISysDataMessageService {
    void delete(SysDataMessageDTO.IdDTO dto);

    SysDataMessageVO.DetailVO detail(SysDataMessageDTO.IdDTO dto);

    PageData<SysDataMessageVO.ListVO> pageData(SysDataMessageQTO.QTO qoDTO);

    void saveSysDataMessage(SysDataMessageDTO.ETO dto);


}
