package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorMenuService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorMenuRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
@DubboService
public class SiteFloorMenuRpc implements ISiteFloorMenuRpc{

    @Autowired
    private ISiteFloorMenuService siteFloorMenuService;

    @Override
    public PageData<SiteFloorMenuVO.ListVO> pageData(SiteFloorMenuQTO.QTO qto){
        return siteFloorMenuService.pageData(qto);
    }

    @Override
    public void addSiteFloorMenu(SiteFloorMenuDTO.ADTO adto){
        siteFloorMenuService.addSiteFloorMenu(adto);
    }

    @Override
    public void deleteSiteFloorMenu(SiteFloorMenuDTO.IdDTO dto){
        siteFloorMenuService.deleteSiteFloorMenu(dto);
    }

    @Override
    public void editSiteFloorMenu(SiteFloorMenuDTO.UDTO udto){
        siteFloorMenuService.editSiteFloorMenu(udto);
    }

    @Override
    public void addBatch(List<SiteFloorMenuDTO.ADTO> adtos) {
        siteFloorMenuService.addBatch(adtos);
    }

    @Override
    public void addSiteBottomLink(SiteFloorMenuDTO.BADTO badto) {
        siteFloorMenuService.addSiteBottomLink(badto);
    }

    @Override
    public void addSiteBottomLinkList(List<SiteFloorMenuDTO.BADTO> badtos) {
        siteFloorMenuService.addSiteBottomLinkList(badtos);
    }

    @Override
    public void editSiteBottomLink(SiteFloorMenuDTO.BUDTO budto) {
        siteFloorMenuService.editSiteBottomLink(budto);
    }

    @Override
    public PageData<SiteFloorMenuVO.BListVO> pageDatalistBottom(SiteFloorMenuQTO.QTO qto) {
        return siteFloorMenuService.pageDatalistBottom(qto);
    }

}