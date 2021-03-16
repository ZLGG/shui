package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.Site;
import com.gs.lshly.biz.support.foundation.mapper.SiteMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class SiteRepositoryImpl extends ServiceImpl<SiteMapper, Site> implements ISiteRepository {

}
