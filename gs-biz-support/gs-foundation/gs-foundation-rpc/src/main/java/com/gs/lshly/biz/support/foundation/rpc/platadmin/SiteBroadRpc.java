package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBroadDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBroadQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBroadVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBroadRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBroadService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class SiteBroadRpc implements ISiteBroadRpc{

    @Autowired
    private ISiteBroadService  SiteBroadService;

    @Override
    public List<SiteBroadVO.ListVO> list(SiteBroadQTO.QTO qto){
        return SiteBroadService.list(qto);
    }


    @Override
    public void editSiteBroad(SiteBroadDTO.ETO eto){
        SiteBroadService.editSiteBroad(eto);
    }


}