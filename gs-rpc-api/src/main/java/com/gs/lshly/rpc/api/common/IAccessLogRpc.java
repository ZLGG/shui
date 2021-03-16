package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.struct.log.AccessLogDTO;

/**
 * 日志服务
 * @author lxus
 * @since 2020-12-20
 */
public interface IAccessLogRpc {

    void save(AccessLogDTO dto);

}
