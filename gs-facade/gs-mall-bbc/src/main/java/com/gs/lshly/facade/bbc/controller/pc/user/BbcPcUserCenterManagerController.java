package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
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
* @since 2020-10-27
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/manager")
@Api(tags = "2C PC会员中心管理")
public class BbcPcUserCenterManagerController {

    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @ApiOperation("个人信息查询")
    @GetMapping("/getEditorUserInfo")
    public ResponseData<BbbUserVO.EditorUserInfoVO> getEditorUserInfo(BaseDTO dto) {
        return ResponseData.data(bbbUserRpc.getEditorUserInfo(dto));
    }

    @ApiOperation("个人信息编辑")
    @PutMapping("/editorUserInfo")
    public ResponseData<Void> editorUserInfo(@Valid @RequestBody BbbUserDTO.UserInfoETO dto) {
        bbbUserRpc.editorUserInfo(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改密码")
    @PutMapping("/editorPassword")
    public ResponseData<Void> editorPassword(@Valid @RequestBody BbbUserDTO.EditorPasswordETO dto) {
        bbbUserRpc.editorPassword(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改支付密码")
    @PutMapping("/editorPayPassword")
    public  ResponseData<Void> editorPayPassword(@Valid @RequestBody BbbUserDTO.EditorPayPasswordDTO dto) {
       bbbUserRpc.editorPayPassword(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("绑定手机号")
    @PutMapping("/bindPhone")
    public ResponseData<Void> bindPhone(@Valid @RequestBody BbbUserDTO.BindPhoneDTO dto) {
        bbbUserRpc.bindPhone(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("绑定邮箱")
    @PutMapping("/bindEmail")
    public ResponseData<Void> bindEmail(@Valid  @RequestBody BbbUserDTO.BindEmailDTO dto) {
        bbbUserRpc.bindEmail(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改用户名")
    @PutMapping("/editorUserName")
    public ResponseData<Void> editorUserName(@Valid  @RequestBody BbbUserDTO.EditorUserNameDTO dto) {
        bbbUserRpc.editorUserName(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("验证当前用户密码")
    @GetMapping("/checkPassword")
    public ResponseData<BbbUserVO.CheckPasswordVO> checkPassword(BbbUserDTO.CheckPasswordETO dto) {
        return ResponseData.data(bbbUserRpc.checkPassword(dto));
    }
}
