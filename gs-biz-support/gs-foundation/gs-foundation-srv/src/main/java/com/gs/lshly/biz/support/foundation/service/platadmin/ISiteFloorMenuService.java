package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuVO;

import java.util.List;

public interface ISiteFloorMenuService {

    PageData<SiteFloorMenuVO.ListVO> pageData(SiteFloorMenuQTO.QTO qto);

    void addSiteFloorMenu(SiteFloorMenuDTO.ADTO adto);

    void deleteSiteFloorMenu(SiteFloorMenuDTO.IdDTO dto);

    void editSiteFloorMenu(SiteFloorMenuDTO.UDTO udto);

    void addBatch(List<SiteFloorMenuDTO.ADTO> adtos);

    void addSiteBottomLink(SiteFloorMenuDTO.BADTO badto);

    void addSiteBottomLinkList(List<SiteFloorMenuDTO.BADTO> badtos);

    void editSiteBottomLink(SiteFloorMenuDTO.BUDTO budto);

    PageData<SiteFloorMenuVO.BListVO> pageDatalistBottom(SiteFloorMenuQTO.QTO qto);
}