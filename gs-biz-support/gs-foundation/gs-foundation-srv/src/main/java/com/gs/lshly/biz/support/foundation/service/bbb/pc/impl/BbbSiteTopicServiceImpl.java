package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteTopic;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteTopicService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteTopicQTO.QTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

/**
* <p>
*  公告通知
* </p>
* @author yingjun
* @since 2020-11-03
*/
@Component
public class BbbSiteTopicServiceImpl implements IBbbSiteTopicService {

    @Autowired
    private ISiteTopicRepository repository;
    
    @Autowired
    private ISiteTopicGoodsRepository goodsRepository;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;

    @Override
    public List<BbbSiteTopicVO.ListVO> list() {
        QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        wrapper.orderByDesc("id");
        List<SiteTopic> list = repository.list(wrapper);
        List<BbbSiteTopicVO.ListVO> retList = new ArrayList<BbbSiteTopicVO.ListVO>();
        retList = BeanUtils.copyList(BbbSiteTopicVO.ListVO.class, list);
        return retList;
    }

	@Override
	public List<DetailVO> listHotsearch(QTO qto) {
		List<GoodsInfoVO.DetailVO> goodsInfoList = new ArrayList<GoodsInfoVO.DetailVO>();
		QueryWrapper<SiteTopic> wrapper = MybatisPlusUtil.query();
		wrapper.eq("terminal", qto.getTerminal());
		wrapper.eq("subject", qto.getSubject());
		wrapper.eq("is_default", TrueFalseEnum.否.getCode());
		wrapper.eq("onoff", TrueFalseEnum.是.getCode());
		wrapper.eq("name", "热门搜索");
		SiteTopic siteTopic = repository.getOne(wrapper);
		if (siteTopic != null) {
			List<BbcSiteTopicVO.CategoryListVO> retListDefault = new ArrayList<BbcSiteTopicVO.CategoryListVO>();
			QueryWrapper<SiteTopicGoods> goodsWrapper = MybatisPlusUtil.query();
			goodsWrapper.eq("topic_id", siteTopic.getId());
			List<SiteTopicGoods> goodslist = goodsRepository.list(goodsWrapper);

			if (CollectionUtils.isNotEmpty(goodslist)) {

				for (SiteTopicGoods siteTopicGoods : goodslist) {

					String goodsId = siteTopicGoods.getGoodsId();
					GoodsInfoVO.DetailVO goodsInfoDetailVO = goodsInfoRpc
							.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
					goodsInfoList.add(goodsInfoDetailVO);

				}
			}
		}
		return goodsInfoList;
	}
}
