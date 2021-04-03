package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteTopicService;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteTopicQTO.QTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO.ListVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteTopicRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午5:38:45
 */
@DubboService
public class BbbSiteTopicRpc implements IBbbSiteTopicRpc {

    @Autowired
    private IBbbSiteTopicService bbbSiteTopicService;

	@Override
	public List<ListVO> list() {
		return bbbSiteTopicService.list();
	}

	@Override
	public List<DetailVO> listHotsearch(QTO qto) {
		return bbbSiteTopicService.listHotsearch(qto);
	}

	



}