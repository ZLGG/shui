package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmTemplateDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmTemplateVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-20
*/
public interface ISysSmRpc {

    List<SysSmVO.ListVO> listSysSM(BaseDTO dto);

    void saveSysSm(SysSmDTO.ETO eto);

    void deleteSysSm(SysSmDTO.IdDTO dto);

    void saveSysSmTemplate(SysSmTemplateDTO.ETO eto);

    void deleteSysSmTemplate(SysSmTemplateDTO.IdDTO dto);

    PageData<SysSmTemplateVO.DetailVO> templatePage(BaseQTO dto);


}