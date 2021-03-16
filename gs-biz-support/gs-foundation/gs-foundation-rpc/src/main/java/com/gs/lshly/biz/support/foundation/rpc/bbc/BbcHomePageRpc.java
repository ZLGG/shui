package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteAdvertDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcHomePageQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbbHomePageVO.CategoryMenuVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcHomePageRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午4:27:32
 */
@DubboService
public class BbcHomePageRpc implements IBbcHomePageRpc{

    @Autowired
    private IBbcSiteAdvertService  bbcSiteAdvertService;

	@Override
	public CategoryMenuVO getCategoryMenuVO(QTO qto) {
		// TODO Auto-generated method stub
		return null;
	}

}