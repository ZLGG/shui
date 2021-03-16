package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbbSiteBannerRpc {

    List<BbbSiteBannerVO.ListVO> list(BbbSiteBannerQTO.QTO qto);
}