package com.gs.lshly.biz.support.foundation.rpc.bbc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteTopicService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.EnjoyQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.SearchmoreQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.CategoryListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.GoodsVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteTopicRpc;;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午10:53:21
 */
@DubboService
public class BbcSiteTopicRpc implements IBbcSiteTopicRpc{

    @Autowired
    private IBbcSiteTopicService  bbcSiteTopicService;

	@Override
	public List<BbcSiteTopicVO.CategoryListVO> list(QTO qto) {
		return bbcSiteTopicService.list(qto);
	}

	@Override
	public PageData<DetailVO> pageEnjoy(EnjoyQTO qto) {
		return bbcSiteTopicService.pageEnjoy(qto);
	}

	@Override
	public List<CategoryListVO> listPointHome(BbcSiteTopicQTO.QTO qto) {
		return bbcSiteTopicService.listPointHome(qto);
	}

	@Override
	public CategoryListVO topicGoods(String topicId) {
		return bbcSiteTopicService.topicGoods(topicId);
	}

	@Override
	public GoodsVO pageMore(SearchmoreQTO qto) {
		return bbcSiteTopicService.pageMore(qto);
	}
	

}