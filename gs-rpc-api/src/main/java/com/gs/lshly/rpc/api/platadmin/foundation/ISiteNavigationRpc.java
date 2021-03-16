package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
public interface ISiteNavigationRpc {

    List<SiteNavigationVO.H5ListVO> h5List(SiteNavigationQTO.H5QTO qto);

    List<SiteNavigationVO.PCListVO> pcList(SiteNavigationQTO.PCQTO qto);

    void h5Editor(SiteNavigationDTO.H5DTO eto);

    void pcEditor(SiteNavigationDTO.PCDTO dto);

    void pcEditor2(SiteNavigationDTO.PC2DTO dto);
}