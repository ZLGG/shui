package com.gs.lshly.biz.support.foundation.repository.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SiteNotice;
import com.gs.lshly.biz.support.foundation.mapper.SiteNoticeMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteNoticeRepository;

/**
 * <p>
 * 站点公告信息 服务实现类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
@Service
public class SiteNoticeRepositoryImpl extends ServiceImpl<SiteNoticeMapper, SiteNotice> implements ISiteNoticeRepository {

}
