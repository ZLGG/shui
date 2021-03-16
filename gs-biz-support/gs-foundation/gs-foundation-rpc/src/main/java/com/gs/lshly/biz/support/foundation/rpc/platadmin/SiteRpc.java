package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-11-11
*/
@DubboService
public class SiteRpc implements ISiteRpc{
    @Autowired
    private ISiteService siteService;

    @Override
    public PageData<SiteVO.ListVO> pageData(SiteQTO.QTO qto){
        return siteService.pageData(qto);
    }

    @Override
    public void editSite(SiteDTO.ETO eto){
        siteService.editSite(eto);
    }

    @Override
    public Boolean merchantCanUsePhoneLogin(BaseDTO dto) {
        return siteService.merchantCanUsePhoneLogin();
    }

}