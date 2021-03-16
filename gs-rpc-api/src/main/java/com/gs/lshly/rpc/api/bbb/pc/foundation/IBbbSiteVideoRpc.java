package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbbSiteVideoRpc {

    List<BbbSiteVideoVO.ListVO> list(BbbSiteVideoQTO.QTO qto);
}