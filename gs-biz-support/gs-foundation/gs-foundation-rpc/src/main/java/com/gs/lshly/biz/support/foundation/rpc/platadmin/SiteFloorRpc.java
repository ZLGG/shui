package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-09-30
*/
@DubboService
public class SiteFloorRpc implements ISiteFloorRpc {

    @Autowired
    private ISiteFloorService siteFloorService;


    @Override
    public List<SiteFloorVO.H5ListVO> h5List(SiteFloorQTO.H5QTO qto) {
        return siteFloorService.h5List(qto);
    }


    @Override
    public void h5Editor(SiteFloorDTO.H5ETO h5ETO){
        siteFloorService.h5Editor(h5ETO);
    }

    @Override
    public List<SiteFloorVO.PCListVO> pcList(SiteFloorQTO.PCQTO qto) {
        return siteFloorService.pcList(qto);
    }

    @Override
    public void pcEditor(SiteFloorDTO.PCETO pcETO) {
        siteFloorService.pcEditor(pcETO);
    }

}