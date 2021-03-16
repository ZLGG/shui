package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.dto.CommonUserAgreementDTO;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ICommonUserAgreementRpc;
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
* @since 2020-10-09
*/
@RestController
@RequestMapping("/platform/userAgreement")
@Api(tags = "会员入驻协议",description = " ")
@Module(code = "membersRgreement", parent = "members", name = "会员注册协议配置", index = 6)
public class UserAgreementController {

    @DubboReference
    private ICommonUserAgreementRpc commonUserAgreementRpc;

    @ApiOperation("查询2B会员协议")
    @GetMapping(value = "")
    @Func(code="view", name="查")
    public ResponseData<CommonUserAgreementVO.DetailVO> get() {
        CommonUserAgreementQTO.QTO qto  = new CommonUserAgreementQTO.QTO();
        qto.setUserType(UserTypeEnum._2B用户.getCode());
        return ResponseData.data(commonUserAgreementRpc.detailUserAgreement(qto));
    }


    @ApiOperation("保存2B会员协议")
    @PostMapping(value = "")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@Valid @RequestBody CommonUserAgreementDTO.ETO eto) {
        eto.setUserType(UserTypeEnum._2B用户.getCode());
        commonUserAgreementRpc.editUserAgreement(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("查询2C会员协议")
    @GetMapping(value = "/2c")
    @Func(code="view", name="查")
    public ResponseData<CommonUserAgreementVO.DetailVO> get2c() {
        CommonUserAgreementQTO.QTO qto  = new CommonUserAgreementQTO.QTO();
        qto.setUserType(UserTypeEnum._2C用户.getCode());
        return ResponseData.data(commonUserAgreementRpc.detailUserAgreement(qto));
    }


    @ApiOperation("保存2C会员协议")
    @PostMapping(value = "/2c")
    @Func(code="edit", name="改")
    public ResponseData<Void> update2c(@Valid @RequestBody CommonUserAgreementDTO.ETO eto) {
        eto.setUserType(UserTypeEnum._2C用户.getCode());
        commonUserAgreementRpc.editUserAgreement(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

}
