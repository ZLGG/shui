package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-09-30
*/
public interface ISiteFloorRpc {

    List<SiteFloorVO.H5ListVO> h5List(SiteFloorQTO.H5QTO qto);

    void h5Editor(SiteFloorDTO.H5ETO h5ETO);

    List<SiteFloorVO.PCListVO> pcList(SiteFloorQTO.PCQTO qto);

    void pcEditor(SiteFloorDTO.PCETO pcETO);

}