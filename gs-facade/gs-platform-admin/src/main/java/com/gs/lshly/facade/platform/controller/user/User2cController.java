package com.gs.lshly.facade.platform.controller.user;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLabelDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLabelDictQTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.user.IUserLabelDictRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/platform/2c/user")
@Api(tags = "会员管理2C",description = " ")
@Module(code = "membersRetail", parent = "members", name = "零售会员", index = 1)
public class User2cController {

    @DubboReference
    private IUserRpc userRpc;

    @DubboReference
    private IUserLabelDictRpc userLabelDictRpc;

    @ApiOperation("会员列表(高级搜索)")
    @PostMapping("/fullSearchList")
    @Func(code="view", name="查")
    public ResponseData<PageData<UserVO.ListVO>> fullSearchList(@RequestBody UserQTO.FullSearchQTO qto) {
        qto.setType(UserTypeEnum._2C用户.getCode());
        return ResponseData.data(userRpc.fullSearchList(qto));
    }

    @ApiOperation("会员列表-v1.1.0")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<UserVO.ListVO>> list(UserQTO.QTO qto) {
        qto.setType(UserTypeEnum._2C用户.getCode());
        return ResponseData.data(userRpc.pageData(qto));
    }

    @ApiOperation("会员详情-v1.1.0")
    @GetMapping("/details/{id}")
    @Func(code="view", name="查")
    public ResponseData<UserVO.DetailVO> details(@PathVariable String id) {
        return ResponseData.data(userRpc.details(new UserDTO.IdDTO(id)));
    }

    @ApiOperation("获取会员已有的标签")
    @GetMapping("/getUserLabel")
    @Func(code="view", name="查")
    public ResponseData<List<UserLabelDictVO.UserLabelVO>> getMoreUserLabel(@Valid @RequestBody UserLabelDictQTO.UserIdListQTO qto) {

        return  ResponseData.data( userLabelDictRpc.getMoreUserLabel(qto));
    }

    @ApiOperation("批量给会员标记标签")
    @PostMapping("/addUserLabel")
    @Func(code="add", name="增")
    public ResponseData<Void> addUserLabel(@Valid @RequestBody UserLabelDictDTO.AddUserLabelDTO dto) {
        userLabelDictRpc.addUserLabel(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("批量删除会员")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid  @RequestBody UserDTO.IdListDTO dto) {
        userRpc.deleteBatchUser(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("编辑会员信息")
    @PutMapping(value = "/editorUserInfo/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorUserInfo(@PathVariable String id, @Valid @RequestBody UserDTO.ETO eto) {
        eto.setId(id);
        userRpc.editorUserInfo(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("编辑会员密码")
    @PutMapping(value = "/editorPassworld/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorPassworld(@PathVariable String id, @Valid @RequestBody UserDTO.PassworldETO eto) {
        eto.setId(id);
        userRpc.editorPassworld(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("编辑会员积分")
    @PutMapping(value = "/editorIntegral/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorIntegral(@PathVariable String id, @Valid @RequestBody UserDTO.IntegralETO eto) {
        eto.setId(id);
        userRpc.editorIntegral(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("编辑会员等级")
    @PutMapping(value = "/editorLeve/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorLeve(@PathVariable String id, @Valid @RequestBody UserDTO.LeveETO eto) {
        eto.setId(id);
        userRpc.editorLeve(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("批量导出会员")
    @GetMapping(value = "/export")
    @Func(code="view", name="查")
    public void export( UserDTO.ExportDTO dto,@ApiIgnore HttpServletResponse response) throws Exception{
        ExportDataDTO exportData = userRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }

}
