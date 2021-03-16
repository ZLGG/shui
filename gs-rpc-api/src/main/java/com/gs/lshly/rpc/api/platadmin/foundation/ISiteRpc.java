package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVO;

/**
*
* @author hyy
* @since 2020-11-11
*/
public interface ISiteRpc {

    PageData<SiteVO.ListVO> pageData(SiteQTO.QTO qto);

    void editSite(SiteDTO.ETO eto);

    Boolean merchantCanUsePhoneLogin(BaseDTO dto);
}