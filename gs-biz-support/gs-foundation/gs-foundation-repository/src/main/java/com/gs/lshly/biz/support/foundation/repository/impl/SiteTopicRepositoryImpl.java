package com.gs.lshly.biz.support.foundation.repository.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SiteTopic;
import com.gs.lshly.biz.support.foundation.mapper.SiteTopicMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicRepository;

/**
 * <p>
 * 站点专题配置信息 服务实现类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
@Service
public class SiteTopicRepositoryImpl extends ServiceImpl<SiteTopicMapper, SiteTopic> implements ISiteTopicRepository {

}
