package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysMailDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysMailVO;

public interface ISysMailService {


    void saveSysMail(SysMailDTO.ETO eto);

    void deleteSysMail(SysMailDTO.IdDTO dto);

    PageData<SysMailVO.ListVO> pageData(BaseQTO qto);
}