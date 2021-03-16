package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuVO;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
public interface ISiteFloorMenuRpc {

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