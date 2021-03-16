package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVO;

public interface ISiteService {

    PageData<SiteVO.ListVO> pageData(SiteQTO.QTO qto);

    void editSite(SiteDTO.ETO eto);

    Boolean merchantCanUsePhoneLogin();
}