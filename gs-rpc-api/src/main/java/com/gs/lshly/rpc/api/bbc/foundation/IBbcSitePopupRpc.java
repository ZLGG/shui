package com.gs.lshly.rpc.api.bbc.foundation;
import java.util.List;

import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSitePopupVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO;

/**
 * 
 * 广告弹窗
 * 
 * @author yingjun
 * @date 2021年3月12日 下午2:01:46
 */
public interface IBbcSitePopupRpc {

	
    List<BbcSiteBannerVO.ListVO> get(BbcSiteBannerQTO.QTO qto);
    
    /**
     * 获取首页广告弹窗数据
     * @param qto
     * @return
     */
    BbcSitePopupVO.DetailVO getPopup(SiteAdvertPopupQTO.BBBPCQTO qto);
    
}