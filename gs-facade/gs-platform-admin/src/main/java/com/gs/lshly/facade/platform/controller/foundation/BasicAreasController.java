package com.gs.lshly.facade.platform.controller.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IBasicAreasRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysDataMessageRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenyang
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/platadmin/basic/area")
@Api(tags = "地区管理")
public class BasicAreasController {

    @DubboReference
    private IBasicAreasRpc iBasicAreasRpc;

    /**
     * 查询浙江城市下拉框
     * @return
     */
    @ApiOperation("城市下拉框")
    @GetMapping("")
    public ResponseData<BasicAreasVO.DropListVO> dropList(@RequestParam(value = "pid",required = false) Integer pid) {
        return ResponseData.data(iBasicAreasRpc.dropList(pid));
    }
}
