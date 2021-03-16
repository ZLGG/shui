package com.gs.lshly.facade.bbb.controller.pc.foundation;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.qto.CommonUserAgreementQTO;
import com.gs.lshly.common.struct.common.vo.CommonUserAgreementVO;
import com.gs.lshly.rpc.api.common.ICommonUserAgreementRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-09
*/
@RestController
@RequestMapping("/bbb/userAgreement")
@Api(tags = "会员入驻协议",description = " ")
public class BbbUserAgreementController {

    @DubboReference
    private ICommonUserAgreementRpc userAgreementRpc;

    @ApiOperation("查询会员协议")
    @GetMapping(value = "")
    public ResponseData<CommonUserAgreementVO.DetailVO> get() {
        CommonUserAgreementQTO.QTO qto  = new CommonUserAgreementQTO.QTO();
        qto.setUserType(UserTypeEnum._2B用户.getCode());
        return ResponseData.data(userAgreementRpc.detailUserAgreement(qto));
    }
}
