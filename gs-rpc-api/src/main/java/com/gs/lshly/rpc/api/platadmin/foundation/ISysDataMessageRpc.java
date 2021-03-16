package com.gs.lshly.rpc.api.platadmin.foundation;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysDataMessageDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface ISysDataMessageRpc {

    PageData<SysDataMessageVO.ListVO> list(SysDataMessageQTO.QTO qoDTO);

    void addSysDataMessage(SysDataMessageDTO.ETO dto);

    void deleteSysDataMessage(SysDataMessageDTO.IdDTO dto);

    SysDataMessageVO.DetailVO getSysDataMessage(SysDataMessageDTO.IdDTO dto);

}
