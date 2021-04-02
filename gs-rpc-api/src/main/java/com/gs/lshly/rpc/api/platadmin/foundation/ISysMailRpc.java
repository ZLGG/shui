package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysMailDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysMailVO;

/**
*
* @author Starry
* @since 2021-03-20
*/
public interface ISysMailRpc {

    void saveSysMail(SysMailDTO.ETO eto);

    void deleteSysMail(SysMailDTO.IdDTO dto);


    PageData<SysMailVO.ListVO> pageData(BaseQTO qto);
}