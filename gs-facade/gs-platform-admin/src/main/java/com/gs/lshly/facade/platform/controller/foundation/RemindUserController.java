package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;
import com.gs.lshly.rpc.api.common.IRemindUserRpc;
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
@RequestMapping("/platadmin/remindUser")
@Api(tags = "平台对用户消息提醒管理")
public class RemindUserController {

    @DubboReference
    private IRemindUserRpc remindUserRpc;

    @ApiOperation("用户消息提醒列表")
    @GetMapping("")
    public ResponseData<PageData<RemindUserVO.ListVO>> list(RemindUserQTO.QTO qto) {
        return ResponseData.data(remindUserRpc.platPageData(qto));
    }

    @ApiOperation("用户消息提醒详情")
    @GetMapping(value = "/{id}")
    public ResponseData<RemindUserVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(remindUserRpc.detailRemindUser(new RemindUserDTO.IdDTO(id)));
    }

}
