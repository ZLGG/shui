package com.gs.lshly.facade.platform.controller.user;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserUser2bApplyDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserUser2bApplyQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserUser2bApplyVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.user.IUserUser2bApplyRpc;
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
* @since 2020-10-06
*/
@RestController
@RequestMapping("/platform/userUser2bApply")
@Api(tags = "2B会员申请管理",description = " ")
@Module(code = "membersReview", parent = "members", name = "会员审核", index = 5)
public class UserUser2bApplyController {

    @DubboReference
    private IUserUser2bApplyRpc userUser2bApplyRpc;

    @ApiOperation("2B会员申请列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<UserUser2bApplyVO.ListVO>> pageList(UserUser2bApplyQTO.QTO qto) {
        return ResponseData.data(userUser2bApplyRpc.pageData(qto));
    }

//    @ApiOperation("2B会员申请详情")
//    @GetMapping(value = "/{id}")
//    public ResponseData<UserUser2bApplyVO.DetailVO> get(@PathVariable String id) {
//        return ResponseData.data(userUser2bApplyRpc.detailUserUser2bApply(new UserUser2bApplyDTO.IdDTO(id)));
//    }

//    @ApiOperation("修改2B会员申请")
//    @PutMapping(value = "/{id}")
//    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody UserUser2bApplyDTO.ETO eto) {
//        eto.setId(id);
//        userUser2bApplyRpc.editUserUser2bApply(eto);
//        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
//    }

    @ApiOperation("批量删除2B会员申请")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody UserUser2bApplyDTO.IdListDTO dto) {
        userUser2bApplyRpc.deleteBatchUserUser2bApply(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("审核2B会员申请")
    @PutMapping(value = "/apply")
    @Func(code="edit", name="改")
    public ResponseData<Void> apply(@Valid @RequestBody UserUser2bApplyDTO.ApplyDTO dto) {
        userUser2bApplyRpc.applyUserUser2bApply(dto);
        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }

}
