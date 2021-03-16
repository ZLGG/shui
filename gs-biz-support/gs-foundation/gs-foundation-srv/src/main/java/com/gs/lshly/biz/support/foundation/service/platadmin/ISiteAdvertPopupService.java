package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO;

public interface ISiteAdvertPopupService {

	/**
	 * 查询广告弹窗列表
	 * @param qto
	 * @return
	 */
	PageData<SiteAdvertPopupVO.PCListVO> pageData(SiteAdvertPopupQTO.QTO qto);
	
	/**
	 * 编辑广告弹窗
	 * @param eto
	 */
	void editor(SiteAdvertPopupDTO.ETO eto);
	
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	SiteAdvertPopupVO.PCDetailVO get(SiteAdvertPopupDTO.IdDTO dto);
	
	/*
	 * 
	 */
	void delete(SiteAdvertPopupDTO.IdDTO dto);
	
	/**
	 * 上下线广告弹窗
	 * @param dto
	 */
	void onoff(SiteAdvertPopupDTO.OnoffDTO dto);

}