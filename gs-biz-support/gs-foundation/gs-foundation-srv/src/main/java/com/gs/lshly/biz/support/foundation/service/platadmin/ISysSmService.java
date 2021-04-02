package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmTemplateDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmTemplateVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;

import java.util.List;

public interface ISysSmService {

    List<SysSmVO.ListVO> listSysSM(BaseDTO dto);

    void saveSysSm(SysSmDTO.ETO eto);

    void deleteSysSm(SysSmDTO.IdDTO dto);

    void saveSysSmTemplate(SysSmTemplateDTO.ETO eto);

    void deleteSysSmTemplate(SysSmTemplateDTO.IdDTO dto);

    PageData<SysSmTemplateVO.DetailVO> templatePage(BaseQTO dto);
}