package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbcSiteVideoRpc {

    List<BbcSiteVideoVO.ListVO> list(BbcSiteVideoQTO.QTO qto);

    BbcSiteVideoVO.ListVO video(BbcSiteVideoQTO.QTO qto);
}