package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteVideoDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteVideoQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVideoVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-20
*/
public interface ISiteVideoRpc {

    List<SiteVideoVO.H5ListVO> h5List(SiteVideoQTO.H5QTO qto);

    void h5Editor(SiteVideoDTO.H5DTO eto);

    List<SiteVideoVO.PCListVO> pcList(SiteVideoQTO.PCQTO qto);

    void pcEditor(SiteVideoDTO.PCDTO udto);
}