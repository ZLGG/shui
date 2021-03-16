package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.mapper.SiteNavigationMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 站点导航(pc) 服务实现类
 * </p>
 *
 * @author 大飞船
 * @since 2020-09-29
 */
@Service
public class SiteNavigationRepositoryImpl extends ServiceImpl<SiteNavigationMapper, SiteNavigation> implements ISiteNavigationRepository {

}
