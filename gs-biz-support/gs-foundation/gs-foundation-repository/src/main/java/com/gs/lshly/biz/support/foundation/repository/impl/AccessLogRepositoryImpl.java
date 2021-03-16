package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.AccessLog;
import com.gs.lshly.biz.support.foundation.mapper.AccessLogMapper;
import com.gs.lshly.biz.support.foundation.repository.IAccessLogRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-12-27
 */
@Service
public class AccessLogRepositoryImpl extends ServiceImpl<AccessLogMapper, AccessLog> implements IAccessLogRepository {

}
