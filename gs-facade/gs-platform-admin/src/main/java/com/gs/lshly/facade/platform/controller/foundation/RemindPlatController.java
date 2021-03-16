package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;
import com.gs.lshly.rpc.api.common.IRemindPlatRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2021-02-05
*/
@RestController
@RequestMapping("/platadmin/remindPlat")
@Api(tags = "平台消息提醒管理")
public class RemindPlatController {

    @DubboReference
    private IRemindPlatRpc RemindPlatRpc;

    @ApiOperation("平台消息提醒列表")
    @GetMapping("")
    public ResponseData<PageData<RemindPlatVO.ListVO>> list(RemindPlatQTO.QTO qto) {
        return ResponseData.data(RemindPlatRpc.pageData(qto));
    }

    @ApiOperation("平台消息提醒详情")
    @GetMapping(value = "/{id}")
    public ResponseData<RemindPlatVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(RemindPlatRpc.detailRemindPlat(new RemindPlatDTO.IdDTO(id)));
    }

}
