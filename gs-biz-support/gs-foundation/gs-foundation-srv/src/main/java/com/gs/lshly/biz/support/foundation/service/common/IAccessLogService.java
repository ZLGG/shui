package com.gs.lshly.biz.support.foundation.service.common;

import com.gs.lshly.common.struct.log.AccessLogDTO;

/**
 * @author lxus
 * @since 2020-12-20
 */
public interface IAccessLogService {

    void save(AccessLogDTO dto);
}
