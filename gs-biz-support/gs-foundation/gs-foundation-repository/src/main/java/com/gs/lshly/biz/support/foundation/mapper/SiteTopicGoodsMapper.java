package com.gs.lshly.biz.support.foundation.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;

/**
 * <p>
 * 站点专题配置信息 Mapper 接口
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
public interface SiteTopicGoodsMapper extends BaseMapper<SiteTopicGoods> {

	@Select("select g.goods_id from gs_site_topic_goods g"+
			" left join gs_site_topic t on g.topic_id = t.id"+
			" where t.`name`='猜你喜欢' and t.onoff=1 and t.`subject`=10 and t.terminal=20")
	IPage<String> pageGoods(IPage pager, @Param(value = "ew") QueryWrapper<SiteTopicGoods> ew );
	
	
	@Select("select gstg.* from gs_site_topic_goods gstg "+
			"left join gs_goods_info ggi on gstg.goods_id = ggi.id "+
			"WHERE "+
			"gstg.topic_id = (select id from gs_site_topic where code=${qw.code} and terminal=${qw.terminal} and SUBJECT=${qw.subject}) "+
			"and ggi.category_id in ('${qw.categoryIds}')")
	IPage<SiteTopicGoods> pageByCodeCategoryIds(IPage<SiteTopicGoods> page,@Param(Constants.WRAPPER) QueryWrapper<BbcSiteTopicQTO.EnjoyQTO> qw);

}
