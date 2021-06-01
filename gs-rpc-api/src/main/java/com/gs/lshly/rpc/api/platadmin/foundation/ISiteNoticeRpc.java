package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:55:09
 */
public interface ISiteNoticeRpc {

	/**
	 * 
	 * @param qto
	 * @return
	 */
	PageData<SiteNoticeVO.PCListVO> pageData(SiteNoticeQTO.QTO qto);
	
	/**
	 * 编辑广告弹窗
	 * @param eto
	 */
	void editor(SiteNoticeDTO.ETO dto);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	SiteNoticeVO.PCDetailVO get(SiteNoticeDTO.IdDTO dto);
	
	/**
	 * 
	 * @param dto
	 */
	void delete(SiteNoticeDTO.IdDTO dto);

	PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto);
}