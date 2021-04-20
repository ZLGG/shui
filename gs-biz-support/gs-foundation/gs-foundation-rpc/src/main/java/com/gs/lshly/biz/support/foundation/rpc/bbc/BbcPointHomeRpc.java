package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBannerDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO.CtccInternationalQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO.SeckillQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO.CtccInternationalListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO.ListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO.SeckillListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcPointHomeRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcPointHomeService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBannerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月14日 下午2:28:58
 */
@DubboService
public class BbcPointHomeRpc implements IBbcPointHomeRpc{

    @Autowired
    private IBbcPointHomeService  bbcPointHomeService;

	@Override
	public List<ListVO> getHome(QTO qto) {
		return bbcPointHomeService.getHome(qto);
	}

//	@Override
//	public List<CtccInternationalListVO> ctccInternationalHome(CtccInternationalQTO qto) {
//		return bbcPointHomeService.ctccInternationalHome(qto);
//	}


}