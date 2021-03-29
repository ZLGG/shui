package com.gs.lshly.biz.support.foundation.rpc.bbc;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteAdvertPopupService;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBroadDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcHomePageQTO.PopupQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO.ListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSitePopupVO.DetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO.BBBPCQTO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSitePopupRpc;

/**
 * 广告弹窗
 *
 * 
 * @author yingjun
 * @date 2021年3月29日 下午3:35:39
 */
@DubboService
public class BbcSitePopupRpc implements IBbcSitePopupRpc{

    @Autowired
    private ISiteAdvertPopupService siteAdvertPopupService;

	@Override
	public List<ListVO> get(QTO qto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailVO getPopup(BBBPCQTO qto) {
		// TODO Auto-generated method stub
		return siteAdvertPopupService.getPopup(qto);
	}

    

}