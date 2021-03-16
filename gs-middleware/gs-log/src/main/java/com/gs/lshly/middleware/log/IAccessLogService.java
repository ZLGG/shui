package com.gs.lshly.middleware.log;

import com.gs.lshly.common.struct.log.AccessLogDTO;

/**
 * 日志服务
 * @author lxus
 * @since 2020-12-20
 */
public interface IAccessLogService {

    void save(AccessLogDTO dto);

}
