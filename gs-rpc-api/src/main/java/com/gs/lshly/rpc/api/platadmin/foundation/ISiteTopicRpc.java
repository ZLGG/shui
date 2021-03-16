package com.gs.lshly.rpc.api.platadmin.foundation;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:55:09
 */
public interface ISiteTopicRpc {

	/**
	 * 分页查询专题配置
	 * @param qto
	 * @return
	 */
	PageData<SiteTopicVO.PCListVO> pageData(SiteTopicQTO.QTO qto);
	
	/**
	 * 编辑专题配置
	 * @param eto
	 */
	void editor(SiteTopicDTO.ETO dto);
	
	/**
	 * 获取专题配置
	 * @param dto
	 * @return
	 */
	SiteTopicVO.PCDetailVO get(SiteTopicDTO.IdDTO dto);
	
	/**
	 * 删除专题配置
	 * @param dto
	 */
	void delete(SiteTopicDTO.IdDTO dto);
	
	/**
	 * 上下线
	 * @param dto
	 */
	void onoff(SiteTopicDTO.OnoffDTO dto);
	
	/**
	 * 查询列有商品列表
	 * @return
	 */
	List<SiteTopicVO.PCGoodsDetailVO> listGoods();
}