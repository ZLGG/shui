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
@Api(tags = "用户消息提醒管理")
public class RemindUserController {

    @DubboReference
    private IRemindUserRpc RemindUserRpc;

    @ApiOperation("用户消息提醒列表")
    @GetMapping("")
    public ResponseData<PageData<RemindUserVO.ListVO>> list(RemindUserQTO.QTO qto) {
        return ResponseData.data(RemindUserRpc.pageData(qto));
    }

    @ApiOperation("用户消息提醒详情")
    @GetMapping(value = "/{id}")
    public ResponseData<RemindUserVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(RemindUserRpc.detailRemindUser(new RemindUserDTO.IdDTO(id)));
    }

    @ApiOperation("新增用户消息提醒")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody RemindUserDTO.ETO dto) {
            RemindUserRpc.addRemindUser(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

//    @ApiOperation("删除用户消息提醒")
//    @DeleteMapping(value = "/{id}")
//    public ResponseData<Void> delete(@PathVariable String id) {
//        RemindUserDTO.IdDTO dto = new RemindUserDTO.IdDTO(id);
//        RemindUserRpc.deleteRemindUser(dto);
//        return ResponseData.success(MsgConst.DELETE_SUCCESS);
//    }
//
//
//    @ApiOperation("修改用户消息提醒")
//    @PutMapping(value = "/{id}")
//    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody RemindUserDTO.ETO eto) {
//        RemindUserRpc.editRemindUser(id,eto);
//        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
//    }

}
