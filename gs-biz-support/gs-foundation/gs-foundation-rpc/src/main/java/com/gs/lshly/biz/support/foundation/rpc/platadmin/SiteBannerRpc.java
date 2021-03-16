package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBannerService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBannerRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
@DubboService
public class SiteBannerRpc implements ISiteBannerRpc{

    @Autowired
    private ISiteBannerService siteBannerService;

    @Override
    public List<SiteBannerVO.H5ListVO> h5List(SiteBannerQTO.H5QTO qto){

        return siteBannerService.h5List(qto);
    }

    @Override
    public List<SiteBannerVO.PCListVO> pcList(SiteBannerQTO.PCQTO qto) {
        return siteBannerService.pcList(qto);
    }

    @Override
    public void h5Editor(SiteBannerDTO.H5DTO udto){

        siteBannerService.h5Editor(udto);
    }

    @Override
    public void pcEditor(SiteBannerDTO.PCDTO dto) {
        siteBannerService.pcEditor(dto);
    }
}