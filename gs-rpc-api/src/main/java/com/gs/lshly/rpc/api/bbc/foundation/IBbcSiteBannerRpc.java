package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbcSiteBannerRpc {

    List<BbcSiteBannerVO.ListVO> list(BbcSiteBannerQTO.QTO qto);
}