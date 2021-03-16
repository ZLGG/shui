package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNoticeService;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO.QTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO.ListVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteNoticeRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午5:38:45
 */
@DubboService
public class BbbSiteNoticeRpc implements IBbbSiteNoticeRpc {

    @Autowired
    private IBbbSiteNoticeService bbbSiteNoticeService;

	@Override
	public List<ListVO> list(QTO qto) {
		return bbbSiteNoticeService.list(qto);
	}

	



}