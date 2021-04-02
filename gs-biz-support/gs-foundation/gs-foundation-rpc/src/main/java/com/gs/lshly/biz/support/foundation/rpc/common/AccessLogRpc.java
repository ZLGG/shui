package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.IAccessLogService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;
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

    @Override
    public PageData<SysAccessLogVO.MerchantLogin> merchantLoginLogs(SysAccessLogQTO.MerchantLogin qto) {
        return logService.merchantLoginLogs(qto);
    }

    @Override
    public void delete(SysAccessLogQTO.IdDTO dto) {
        logService.delete(dto);
    }

    @Override
    public PageData<SysAccessLogVO.OperatorLogin> operatorLoginLogs(SysAccessLogQTO.Operator qto) {
        return logService.operatorLoginLogs(qto);
    }

    @Override
    public PageData<SysAccessLogVO.Operator> operatorWithoutLoginLogs(SysAccessLogQTO.Operator qto) {
        return logService.operatorWithoutLoginLogs(qto);
    }

    @Override
    public ExportDataDTO export(SysAccessLogQTO.Operator qto) {
        return logService.export(qto);
    }

    @Override
    public PageData<SysAccessLogVO.UserLogin> userLoginLogs(SysAccessLogQTO.UserLogin qto) {
        return logService.userLoginLogs(qto);
    }
}
