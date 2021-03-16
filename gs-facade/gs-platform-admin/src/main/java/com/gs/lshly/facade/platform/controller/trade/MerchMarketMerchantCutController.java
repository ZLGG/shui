package com.gs.lshly.facade.platform.controller.trade;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCutRpc;
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
@RequestMapping("/platform/marketMerchantCut")
@Api(tags = "平台满减管理")
@Module(code = "fullReduction",parent = "marketing",name = "满减",index = 3)
public class MerchMarketMerchantCutController {

    @DubboReference
    private IMarketPtMerchantCutRpc iMarketPtMerchantCutRpc;

    @ApiOperation("平台满减列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketMerchantCutVO.PlatformView>> list(PCMerchMarketMerchantCutQTO.QTO qto) {
        return ResponseData.data(iMarketPtMerchantCutRpc.view(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketMerchantCutVO.PlatformCutView> get(@PathVariable String id) {
        return ResponseData.data(iMarketPtMerchantCutRpc.get(new PCMerchMarketMerchantCutDTO.IdDTO(id)));
    }
    @ApiOperation("审核")
    @PostMapping(value = "/check/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> check(@Valid @RequestBody PCMerchMarketMerchantCutDTO.Check dto) {
        iMarketPtMerchantCutRpc.check(dto);

        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }


}
