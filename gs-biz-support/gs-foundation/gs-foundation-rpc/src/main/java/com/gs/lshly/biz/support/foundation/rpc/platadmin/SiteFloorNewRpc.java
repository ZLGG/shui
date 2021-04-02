package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorNewDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorNewVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorNewRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorNewService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-10
*/
@DubboService
public class SiteFloorNewRpc implements ISiteFloorNewRpc{
    @Autowired
    private ISiteFloorNewService  SiteFloorNewService;


    @Override
    public List<SiteFloorNewVO.ListVO> listSiteFloorNewVO(SiteFloorNewDTO.ShowDTO dto) {
        return SiteFloorNewService.listSiteFloorNewVO(dto);
    }

    @Override
    public void saveSiteFloorNew(List<SiteFloorNewDTO.ETO> etoList) {

        SiteFloorNewService.saveSiteFloorNew(etoList);
    }
}