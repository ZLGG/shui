package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteNavigationService;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNavigationRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
@DubboService
public class SiteNavigationRpc implements ISiteNavigationRpc{

    @Autowired
    private ISiteNavigationService siteNavigationService;

    @Override
    public List<SiteNavigationVO.H5ListVO> h5List(SiteNavigationQTO.H5QTO qto) {
        return siteNavigationService.h5List(qto);
    }

    @Override
    public List<SiteNavigationVO.PCListVO> pcList(SiteNavigationQTO.PCQTO qto){
        return siteNavigationService.pcList(qto);
    }

    @Override
    public void h5Editor(SiteNavigationDTO.H5DTO eto){
        siteNavigationService.h5Editor(eto);
    }

    @Override
    public void pcEditor(SiteNavigationDTO.PCDTO udtos) {
        siteNavigationService.pcEditor(udtos);
    }

    @Override
    public void pcEditor2(SiteNavigationDTO.PC2DTO dto) {
        siteNavigationService.pcEditor2(dto);
    }


}