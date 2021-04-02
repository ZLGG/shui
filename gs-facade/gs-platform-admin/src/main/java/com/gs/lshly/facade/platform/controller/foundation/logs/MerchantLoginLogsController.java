package com.gs.lshly.facade.platform.controller.foundation.logs;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;
import com.gs.lshly.rpc.api.common.IAccessLogRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "商家登录日志")
public class MerchantLoginLogsController {

    @DubboReference
    private IAccessLogRpc accessLogRpc;

    @ApiOperation("商家登录日志")
    @GetMapping(value = "merchant/login")
    public ResponseData<PageData<SysAccessLogVO.MerchantLogin>> merchantLoginLogs(SysAccessLogQTO.MerchantLogin qto) {
        return ResponseData.data(accessLogRpc.merchantLoginLogs(qto));
    }

    @ApiOperation("商家登录日志-删除")
    @PostMapping(value = "merchant/login")
    public ResponseData<Void> merchantLoginLogsDelete(@RequestBody SysAccessLogQTO.IdDTO ids) {
        accessLogRpc.delete(ids);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
