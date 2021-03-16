package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;

import java.util.List;

public interface ISiteBannerService {

    List<SiteBannerVO.H5ListVO> h5List(SiteBannerQTO.H5QTO qto);

    void h5Editor(SiteBannerDTO.H5DTO udto);

    List<SiteBannerVO.PCListVO> pcList(SiteBannerQTO.PCQTO qto);

    void pcEditor(SiteBannerDTO.PCDTO dto);
}