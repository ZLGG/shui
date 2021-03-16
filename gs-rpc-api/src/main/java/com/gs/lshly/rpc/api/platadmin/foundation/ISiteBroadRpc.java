package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBroadDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBroadQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBroadVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface ISiteBroadRpc {

    List<SiteBroadVO.ListVO> list(SiteBroadQTO.QTO qto);

    void editSiteBroad(SiteBroadDTO.ETO eto);

}