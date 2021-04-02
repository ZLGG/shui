package com.gs.lshly.facade.platform.controller.foundation.logs;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.common.IAccessLogRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
@Api(tags = "平台操作日志")
public class OperatorLogsController {

    @DubboReference
    private IAccessLogRpc accessLogRpc;

    @ApiOperation("平台操作日志(排除登陆)")
    @GetMapping("operator")
    public ResponseData<ResponseData<SysAccessLogVO.Operator>> list(SysAccessLogQTO.Operator qto) {
        return ResponseData.data(accessLogRpc.operatorWithoutLoginLogs(qto));
    }

    @ApiOperation("平台操作日志-导出(排除登陆)")
    @GetMapping("operator/export")
    public void export(SysAccessLogQTO.Operator qto,@ApiIgnore HttpServletResponse response) throws IOException {
        ExportDataDTO exportData = accessLogRpc.export(qto);
        ExcelUtil.export(exportData, response);
    }

}
