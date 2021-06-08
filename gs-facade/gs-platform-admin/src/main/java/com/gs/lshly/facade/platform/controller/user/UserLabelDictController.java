package com.gs.lshly.facade.platform.controller.user;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLabelDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLabelDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.user.IUserLabelDictRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-05
*/
@RestController
@RequestMapping("/platform/userLabelDict")
@Api(tags = "会员标签数据管理",description = " ")
@Module(code = "membersTag", parent = "members", name = "用户标签", index = 2)
public class  UserLabelDictController {

    @DubboReference
    private IUserLabelDictRpc userLabelDictRpc;

    @ApiOperation("会员标签数据分页列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<UserLabelDictVO.ListVO>> pageList(UserLabelDictQTO.QTO qto) {
        return ResponseData.data(userLabelDictRpc.pageData(qto));
    }

    @ApiOperation("会员标签数据全部数据")
    @GetMapping("/list")
    @Func(code="view", name="查")
    public ResponseData<List<UserLabelDictVO.ListVO>> list() {
        return ResponseData.data(userLabelDictRpc.list());
    }

    @ApiOperation("新增会员标签数据")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> add(@Valid @RequestBody UserLabelDictDTO.ETO dto) {
        userLabelDictRpc.addUserLabelDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("批量删除会员标签数据")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody UserLabelDictDTO.IdListDTO dto) {
        userLabelDictRpc.deleteBatchUserLabelDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("编辑会员标签数据")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody UserLabelDictDTO.ETO eto) {
        eto.setId(id);
        userLabelDictRpc.editUserLabelDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }





}
