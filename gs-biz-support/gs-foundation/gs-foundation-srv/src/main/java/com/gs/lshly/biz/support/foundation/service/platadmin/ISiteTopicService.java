package com.gs.lshly.biz.support.foundation.service.platadmin;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;

public interface ISiteTopicService {

	/**
	 * 查询专题配置列表
	 * @param qto
	 * @return
	 */
	PageData<SiteTopicVO.PCListVO> pageData(SiteTopicQTO.QTO qto);
	
	/**
	 * 编辑专题配置
	 * @param eto
	 */
	void editor(SiteTopicDTO.ETO eto);
	
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	SiteTopicVO.PCDetailVO get(SiteTopicDTO.IdDTO dto);
	
	/*
	 * 
	 */
	void delete(SiteTopicDTO.IdDTO dto);
	
	/**
	 * 上下线专题配置
	 * @param dto
	 */
	void onoff(SiteTopicDTO.OnoffDTO dto);
	
	/**
	 * 
	 * @return
	 */
	List<SiteTopicVO.PCGoodsDetailVO> listGoods();

}