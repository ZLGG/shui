package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorActivityDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorActivityQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorActivityVO;

/**
*
* @author zdf
* @since 2021-03-20
*/
public interface ISiteFloorActivityRpc {



    SiteFloorActivityVO.ListVO getAdvertActivityList();

    void advertActivityEditor(SiteFloorActivityDTO.ETO eto);
}