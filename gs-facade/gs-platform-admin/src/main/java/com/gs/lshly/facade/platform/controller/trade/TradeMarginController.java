package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeMarginRpc;
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
* @author zst
* @since 2020-12-09
*/
@RestController
@RequestMapping("/platadmin/tradeMargin")
@Api(tags = "保证金列表管理")
@Module(code = "marginManagement",parent = "finance",name = "保证金管理" ,index = 3)
public class TradeMarginController {

    @DubboReference
    private ITradeMarginRpc tradeMarginRpc;

    @ApiOperation("保证金列表")
    @GetMapping("/list")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeMarginVO.ListVO>> list(TradeMarginQTO.marginQTO qto) {
        return ResponseData.data(tradeMarginRpc.pageData(qto));
    }

    @ApiOperation("保证金详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view" ,name = "查看")
    public ResponseData<TradeMarginVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(tradeMarginRpc.detailTradeMargin(new TradeMarginDTO.IdDTO(id)));
    }


    @ApiOperation("额度调整")
    @PutMapping(value = "/updateByQuota/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData updateByQuota(@PathVariable String id ,@Valid @RequestBody TradeMarginDTO.QuotaDTO eto) {
        eto.setId(id);
        tradeMarginRpc.updateByQuota(eto);
        return ResponseData.success("额度调整成功");
    }

    @ApiOperation("充值")
    @PutMapping(value = "/updateByCharge/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData updateByCharge(@PathVariable String id ,@Valid @RequestBody TradeMarginDTO.ChargeDTO eto) {
        eto.setId(id);
        tradeMarginRpc.updateByCharge(eto);
        return ResponseData.success("充值成功");
    }

    @ApiOperation("扣款")
    @PutMapping(value = "/updateByDeduction/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData updateByDeduction(@PathVariable String id ,@Valid @RequestBody TradeMarginDTO.DeductionDTO eto) {
        eto.setId(id);
        tradeMarginRpc.updateByDeduction(eto);
        return ResponseData.success("扣款成功");
    }

}
