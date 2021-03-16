package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;

import java.util.List;

public interface ISiteFloorService {

    List<SiteFloorVO.H5ListVO> h5List(SiteFloorQTO.H5QTO qto);


    void h5Editor(SiteFloorDTO.H5ETO h5ETO);

    List<SiteFloorVO.PCListVO> pcList(SiteFloorQTO.PCQTO qto);

    void pcEditor(SiteFloorDTO.PCETO pcETO);

}