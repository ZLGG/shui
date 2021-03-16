package com.gs.lshly.rpc.api.bbb.h5.foundation;

import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteNavigationVO;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbbH5SiteNavigationRpc {

    List<BbbH5SiteNavigationVO.ListVO> list(BbbH5SiteNavigationQTO.QTO qto);

}