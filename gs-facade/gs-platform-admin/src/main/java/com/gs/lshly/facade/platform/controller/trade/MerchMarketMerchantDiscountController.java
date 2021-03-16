package com.gs.lshly.facade.platform.controller.trade;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCutRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantDiscountRpc;
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
@RequestMapping("/platform/marketMerchantDiscount")
@Api(tags = "平台满折管理")
@Module(code = "limitDiscounts",parent = "marketing",name = "限时折扣",index = 2)
public class MerchMarketMerchantDiscountController {

    @DubboReference
    private IMarketPtMerchantDiscountRpc iMarketPtMerchantDiscountRpc;

    @ApiOperation("平台满折列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketMerchantDiscountVO.PlatformView>> list(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        return ResponseData.data(iMarketPtMerchantDiscountRpc.view(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketMerchantDiscountVO.PlatformCutView> get(@PathVariable String id) {
        PCMerchMarketMerchantDiscountDTO.IdDTO idDTO = new PCMerchMarketMerchantDiscountDTO.IdDTO(id);
        return ResponseData.data(iMarketPtMerchantDiscountRpc.get(idDTO));
    }
    @ApiOperation("审核")
    @PostMapping(value = "/check/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> check(@Valid @RequestBody PCMerchMarketMerchantDiscountDTO.Check dto) {
        iMarketPtMerchantDiscountRpc.check(dto);

        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }


}
