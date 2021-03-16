package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchantAgreementVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.PCMerchSiteVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchantAgreementRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IPCMerchSiteRpc;
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
* @since 2020-10-23
*/
@RestController
@RequestMapping("/oauth/front")
@Api(tags = "商家登录前接口组管理",description = " ")
public class PCMerchFrontController {

    @DubboReference
    private IPCMerchMerchantAccountRpc pcMerchMerchantAccountRpc;

    @DubboReference
    private IPCMerchSiteRpc pcMerchSiteRpc;

    @DubboReference
    private IPCMerchantAgreementRpc agreementRpc;


    @ApiOperation("检查用户名是否存在")
    @GetMapping("/checkUserName")
    public ResponseData<Boolean> checkUserName(String userName) {
        return ResponseData.data(pcMerchMerchantAccountRpc.checkUserName(new PCMerchMerchantAccountDTO.CheckUserNameDTO(userName)));
    }



    @ApiOperation("注册")
    @PostMapping("/reg")
    public ResponseData<String> add(@Valid @RequestBody PCMerchMerchantAccountDTO.RegDTO dto) {

        return ResponseData.data(pcMerchMerchantAccountRpc.regMerchantAccount(dto));
    }

    @ApiOperation("商家登录背景图信息")
    @GetMapping("/getLoginImageVo")
    public ResponseData<PCMerchSiteVO.LoginImageVO> getImageVO(BaseDTO dto) {
        return ResponseData.data(pcMerchSiteRpc.getLoginImageVO(dto));
    }


    @ApiOperation("获取入驻协议或注册协议")
    @GetMapping("/getMerchantAgreement")
    public ResponseData<PCMerchantAgreementVO.DetailVO> getAgreementVO(BaseDTO dto) {
        return ResponseData.data(agreementRpc.detailMerchantAgreement(dto));
    }


}
