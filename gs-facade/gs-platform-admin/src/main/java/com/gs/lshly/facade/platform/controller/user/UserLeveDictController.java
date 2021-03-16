package com.gs.lshly.facade.platform.controller.user;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLeveDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLeveDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLeveDictVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.user.IUserLeveDictRpc;
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
* @since 2020-10-05
*/
@RestController
@RequestMapping("/platform/userLeveDict")
@Api(tags = "会员等级数据管理",description = " ")
@Module(code = "memberLevel", parent = "members", name = "会员等级", index = 3)
public class UserLeveDictController {

    @DubboReference
    private IUserLeveDictRpc userLeveDictRpc;

    @ApiOperation("会员等级数据列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<UserLeveDictVO.ListVO>> list(UserLeveDictQTO.QTO qto) {
        return ResponseData.data(userLeveDictRpc.pageData(qto));
    }

    @ApiOperation("会员等级数据详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<UserLeveDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(userLeveDictRpc.detailUserLeveDict(new UserLeveDictDTO.IdDTO(id)));
    }

    @ApiOperation("新增会员等级数据")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> add(@Valid @RequestBody UserLeveDictDTO.ETO dto) {
        userLeveDictRpc.addUserLeveDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除会员等级数据")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody UserLeveDictDTO.IdListDTO dto) {
        userLeveDictRpc.deleteBatchUserLeveDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改会员等级数据")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody UserLeveDictDTO.ETO eto) {
        eto.setId(id);
        userLeveDictRpc.editUserLeveDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("会员等级列表(不分页)")
    @GetMapping(value = "/listLevel")
    @Func(code="view", name="查")
    public ResponseData<UserLeveDictVO.LevelListVO> listLevel(UserLeveDictDTO.UsingTypeDTO dto) {
        return ResponseData.data(userLeveDictRpc.lisLevelListVO(dto));
    }

}
