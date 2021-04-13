package com.gs.lshly.biz.support.foundation.repository.impl;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.biz.support.foundation.mapper.SiteTopicGoodsMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicGoodsRepository;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.EnjoyQTO;

/**
 * <p>
 * 站点专题配置信息 服务实现类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
@Service
public class SiteTopicGoodsRepositoryImpl extends ServiceImpl<SiteTopicGoodsMapper, SiteTopicGoods> implements ISiteTopicGoodsRepository {

}
