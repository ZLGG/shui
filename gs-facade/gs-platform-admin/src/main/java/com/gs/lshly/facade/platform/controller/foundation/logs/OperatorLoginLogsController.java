package com.gs.lshly.facade.platform.controller.foundation.logs;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;
import com.gs.lshly.rpc.api.common.IAccessLogRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/platform/logs")
@Api(tags = "平台登陆日志")
public class OperatorLoginLogsController {

    @DubboReference
    private IAccessLogRpc accessLogRpc;

    @ApiOperation("平台登陆日志")
    @GetMapping("login")
    public ResponseData<ResponseData<SysAccessLogVO.OperatorLogin>> loginLogs(SysAccessLogQTO.Operator qto) {
        return ResponseData.data(accessLogRpc.operatorLoginLogs(qto));
    }


}
