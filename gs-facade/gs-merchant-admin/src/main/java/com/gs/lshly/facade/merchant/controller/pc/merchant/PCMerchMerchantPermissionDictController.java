package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantPermissionDictQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantPermissionDictRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-26
*/
@RestController
@RequestMapping("/merchadmin/merchantPermissionDict")
@Api(tags = "商家帐号角色权限字典管理",description = " ")
public class PCMerchMerchantPermissionDictController {

    @DubboReference
    private IPCMerchMerchantPermissionDictRpc pcMerchMerchantPermissionDictRpc;

    @ApiOperation("商家帐号角色权限字典列表")
    @GetMapping("")
    public ResponseData<List<PCMerchMerchantPermissionDictVO.ListVO>> list(PCMerchMerchantPermissionDictQTO.QTO qto) {
        return ResponseData.data(pcMerchMerchantPermissionDictRpc.list(qto));
    }

}
