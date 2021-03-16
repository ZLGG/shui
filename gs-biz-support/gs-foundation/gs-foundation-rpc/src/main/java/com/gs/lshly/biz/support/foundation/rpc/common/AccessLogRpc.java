package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.IAccessLogService;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.rpc.api.common.IAccessLogRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 访问日志持久化
 * @author lxus
 * @since 2020-12-12
 */
@Slf4j
@DubboService
public class AccessLogRpc implements IAccessLogRpc {

    @Autowired
    private IAccessLogService logService;

    @Override
    public void save(AccessLogDTO dto) {
        log.info("访问日志持久化:" + JsonUtils.toJson(dto));
        logService.save(dto);
    }
}
