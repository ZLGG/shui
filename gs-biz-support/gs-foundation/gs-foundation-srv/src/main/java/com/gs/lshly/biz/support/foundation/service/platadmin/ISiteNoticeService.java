package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO;

public interface ISiteNoticeService {

	/**
	 * 查询公告配置列表
	 * @param qto
	 * @return
	 */
	PageData<SiteNoticeVO.PCListVO> pageData(SiteNoticeQTO.QTO qto);
	
	/**
	 * 编辑公告配置
	 * @param eto
	 */
	void editor(SiteNoticeDTO.ETO eto);
	
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	SiteNoticeVO.PCDetailVO get(SiteNoticeDTO.IdDTO dto);
	
	/*
	 * 
	 */
	void delete(SiteNoticeDTO.IdDTO dto);


	PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto);
}