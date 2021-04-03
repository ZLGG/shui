package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:55:09
 */
public interface ISiteAdvertPopupRpc {

	/**
	 * 分页查询广告弹窗
	 * @param qto
	 * @return
	 */
	PageData<SiteAdvertPopupVO.PCListVO> pageData(SiteAdvertPopupQTO.QTO qto);
	
	/**
	 * 编辑广告弹窗
	 * @param eto
	 */
	void editor(SiteAdvertPopupDTO.ETO dto);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	SiteAdvertPopupVO.PCDetailVO get(SiteAdvertPopupDTO.IdDTO dto);
	
	/**
	 * 
	 * @param dto
	 */
	void delete(SiteAdvertPopupDTO.IdDTO dto);
	
	/**
	 * 
	 * @param dto
	 */
	void onoff(SiteAdvertPopupDTO.OnoffDTO dto);
}