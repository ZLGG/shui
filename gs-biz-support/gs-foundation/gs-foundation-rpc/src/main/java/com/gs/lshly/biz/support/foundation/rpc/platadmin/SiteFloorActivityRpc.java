package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorActivityDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorActivityQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorActivityVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorActivityRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2021-03-20
*/
@DubboService
public class SiteFloorActivityRpc implements ISiteFloorActivityRpc{
    @Autowired
    private ISiteFloorActivityService  SiteFloorActivityService;


    @Override
    public SiteFloorActivityVO.ListVO getAdvertActivityList() {
        return SiteFloorActivityService.getAdvertActivityList();
    }

    @Override
    public void advertActivityEditor(SiteFloorActivityDTO.ETO eto) {
        SiteFloorActivityService.advertActivityEditor(eto);
    }

}