package com.gs.lshly.biz.support.foundation.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
/**
 * <p>
 * 站点专题配置信息 服务类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
public interface ISiteTopicGoodsRepository extends IService<SiteTopicGoods> {
	
	
}
