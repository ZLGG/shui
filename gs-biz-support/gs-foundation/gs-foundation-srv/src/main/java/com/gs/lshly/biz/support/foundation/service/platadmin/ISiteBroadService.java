package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBroadDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBroadQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBroadVO;

import java.util.List;

public interface ISiteBroadService {

    List<SiteBroadVO.ListVO> list(SiteBroadQTO.QTO qto);


    void editSiteBroad(SiteBroadDTO.ETO eto);


}