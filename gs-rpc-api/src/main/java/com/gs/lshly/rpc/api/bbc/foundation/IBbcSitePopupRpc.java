package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;

import java.util.List;

/**
 * 
 * 广告弹窗
 * 
 * @author yingjun
 * @date 2021年3月12日 下午2:01:46
 */
public interface IBbcSitePopupRpc {

	
    List<BbcSiteBannerVO.ListVO> get(BbcSiteBannerQTO.QTO qto);
}