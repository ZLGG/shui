package com.gs.lshly.facade.platform.controller.trade;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantDiscountRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantGiftRpc;
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
@RequestMapping("/platform/marketMerchantGift")
@Api(tags = "平台满赠管理")
//@Module(code = "fullGift",parent = "marketing",name = "满赠",index = 4)
public class MerchMarketMerchantGiftController {

    @DubboReference
    private IMarketPtMerchantGiftRpc iMarketPtMerchantGiftRpc;

    @ApiOperation("平台满赠列表")
    @GetMapping("")
    //@Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketMerchantGiftVO.PlatformView>> list(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        return ResponseData.data(iMarketPtMerchantGiftRpc.view(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    //@Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketMerchantGiftVO.PlatformCutView> get(@PathVariable String id) {
        return ResponseData.data(iMarketPtMerchantGiftRpc.get(new PCMerchMarketMerchantGiftDTO.IdDTO(id)));
    }
    @ApiOperation("审核")
    @PostMapping(value = "/check/{id}")
    //@Func(code = "edit",name = "改")
    public ResponseData<Void> check(@Valid @RequestBody PCMerchMarketMerchantGiftDTO.Check dto) {
        iMarketPtMerchantGiftRpc.check(dto);

        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }


}
