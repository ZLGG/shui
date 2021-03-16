package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchSettingsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchSettingsVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* <p>
*  店铺配置管理 前端控制器
* </p>
*
* @author lxus
* @since 2020-10-30
*/
@RestController
@RequestMapping("/merchant/settings")
@Api(tags = "店铺配置管理")
@Module(code = "storeConfiguration", parent = "shop", name = "店铺配置", index = 1)
public class PCMerchSettingsController {

    @DubboReference
    private IPCMerchShopRpc shopRpc;

    @ApiOperation("获取-配送方式设置")
    @GetMapping("/delivery")
    @Func(code="view", name="查")
    public ResponseData<PCMerchSettingsVO.DeliveryStyleVO> deliveryStyle(){
        return ResponseData.data(shopRpc.getDeliveryStyle(new BaseDTO()));
    }

    @ApiOperation("配送方式-设置")
    @PostMapping("/delivery")
    @Func(code="edit", name="改")
    public ResponseData<Void> deliveryStyle(@Valid @RequestBody PCMerchSettingsDTO.DeliveryStyleDTO dto){
        shopRpc.setDeliveryStyle(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
