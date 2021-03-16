package com.gs.lshly.rpc.api.bbc.foundation;

import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNavigationQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteNavigationVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbcSiteNavigationRpc {

    List<BbcSiteNavigationVO.ListVO> list(BbcSiteNavigationQTO.QTO qto);

}