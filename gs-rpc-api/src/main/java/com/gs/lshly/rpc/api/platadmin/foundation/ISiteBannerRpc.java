package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
public interface ISiteBannerRpc {

    List<SiteBannerVO.H5ListVO> h5List(SiteBannerQTO.H5QTO qto);

    List<SiteBannerVO.PCListVO> pcList(SiteBannerQTO.PCQTO qto);

    void h5Editor(SiteBannerDTO.H5DTO udto);

    void pcEditor(SiteBannerDTO.PCDTO dto);


}