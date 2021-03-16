package com.gs.lshly.facade.platform.controller.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantAgreementRpc;
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
@RequestMapping("/platform/merchantAgreement")
@Api(tags = "商家入驻协议管理",description = " ")
@Module(code = "agreementRegistration", parent = "bussiness", name = "注册协议", index = 7)
public class MerchantAgreementController {

    @DubboReference
    private IMerchantAgreementRpc merchantAgreementRpc;


    @ApiOperation("商家入驻协议详情")
    @GetMapping(value = "")
    @Func(code="view", name="查")
    public ResponseData<MerchantAgreementVO.DetailVO> get() {
        return ResponseData.data(merchantAgreementRpc.detailMerchantAgreement(new MerchantAgreementDTO.IdDTO("")));
    }

    @ApiOperation("商家入驻协议编辑")
    @PutMapping(value = "")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@Valid @RequestBody MerchantAgreementDTO.ETO eto) {
        merchantAgreementRpc.editMerchantAgreement(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

}
