package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteAdvertPopupService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.OnoffDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO.PCListVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertPopupRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午3:01:50
 */
@DubboService
public class SiteAdvertPopupRpc implements ISiteAdvertPopupRpc{

    @Autowired
    private ISiteAdvertPopupService  siteAdvertPopupServic;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		return siteAdvertPopupServic.pageData(qto);
	}

	@Override
	public void editor(ETO eto) {
		siteAdvertPopupServic.editor(eto);
		
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		return siteAdvertPopupServic.get(dto);
	}

	@Override
	public void delete(IdDTO dto) {
		siteAdvertPopupServic.delete(dto);
		
	}

	@Override
	public void onoff(OnoffDTO dto) {
		siteAdvertPopupServic.onoff(dto);
	}
   
}