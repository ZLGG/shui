package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteTopicService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.OnoffDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCGoodsDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCListVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteTopicRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午3:01:50
 */
@DubboService
public class SiteTopicRpc implements ISiteTopicRpc{

    @Autowired
    private ISiteTopicService  siteTopicServic;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		return siteTopicServic.pageData(qto);
	}

	@Override
	public void editor(ETO eto) {
		siteTopicServic.editor(eto);
		
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		return siteTopicServic.get(dto);
	}

	@Override
	public void delete(IdDTO dto) {
		siteTopicServic.delete(dto);
		
	}

	@Override
	public void onoff(OnoffDTO dto) {
		siteTopicServic.onoff(dto);
	}

	@Override
	public List<PCGoodsDetailVO> listGoods() {
		return siteTopicServic.listGoods();
	}
   
}