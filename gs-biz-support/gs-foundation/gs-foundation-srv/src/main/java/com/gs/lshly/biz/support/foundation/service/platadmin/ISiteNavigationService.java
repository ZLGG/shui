package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;

import java.util.List;

public interface ISiteNavigationService {

    List<SiteNavigationVO.H5ListVO> h5List(SiteNavigationQTO.H5QTO qto);

    List<SiteNavigationVO.PCListVO> pcList(SiteNavigationQTO.PCQTO qto);

    void h5Editor(SiteNavigationDTO.H5DTO eto);

    void pcEditor(SiteNavigationDTO.PCDTO dto);

    void pcEditor2(SiteNavigationDTO.PC2DTO dto);
}