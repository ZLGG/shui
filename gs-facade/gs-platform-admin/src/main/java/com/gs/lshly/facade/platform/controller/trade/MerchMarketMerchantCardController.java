package com.gs.lshly.facade.platform.controller.trade;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCardRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCardRpc;
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
 * @author zdf
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/platform/marketMerchantCard")
@Api(tags = "平台优惠卷管理")
@Module(code = "merchantCoupons",parent = "marketing",name = "商家优惠券",index = 5)
public class MerchMarketMerchantCardController {

    @DubboReference
    private IMarketPtMerchantCardRpc iMarketPtMerchantCardRpc;

    @ApiOperation("平台优惠卷列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketMerchantCardVO.DetailVO>> list(PCMerchMarketMerchantCardQTO.QTO qto) {
        return ResponseData.data(iMarketPtMerchantCardRpc.view(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketMerchantCardVO.PlatformView> get(@PathVariable String id) {
        return ResponseData.data(iMarketPtMerchantCardRpc.get(new PCMerchMarketMerchantCardDTO.IdDTO(id)));
    }
    @ApiOperation("审核")
    @PostMapping(value = "/check/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> check(@Valid @RequestBody PCMerchMarketMerchantCardDTO.Check dto) {
        iMarketPtMerchantCardRpc.check(dto);

        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }


}
