package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteVideoDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteVideoQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVideoVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteVideoRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteVideoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-20
*/
@DubboService
public class SiteVideoRpc implements ISiteVideoRpc{

    @Autowired
    private ISiteVideoService  SiteVideoService;

    @Override
    public List<SiteVideoVO.H5ListVO> h5List(SiteVideoQTO.H5QTO qto){

        return SiteVideoService.h5List(qto);
    }

    @Override
    public void h5Editor(SiteVideoDTO.H5DTO eto){
        SiteVideoService.h5Editor(eto);
    }

    @Override
    public List<SiteVideoVO.PCListVO> pcList(SiteVideoQTO.PCQTO qto) {
        return SiteVideoService.pcList(qto);
    }

    @Override
    public void pcEditor(SiteVideoDTO.PCDTO udto) {
        SiteVideoService.pcEditor(udto);
    }


}