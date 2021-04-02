package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorActivityDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorActivityQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorActivityVO;

public interface ISiteFloorActivityService {


    SiteFloorActivityVO.ListVO getAdvertActivityList();

    void advertActivityEditor(SiteFloorActivityDTO.ETO eto);
}