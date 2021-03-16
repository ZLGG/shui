package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.SiteNotice;
import com.gs.lshly.biz.support.foundation.mapper.SiteNoticeMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteNoticeRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
