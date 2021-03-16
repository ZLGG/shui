package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDeliveryDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeDeliveryRpc;
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
* @author oy
* @since 2020-11-16
*/
@RestController
@RequestMapping("/platadmin/tradeDelivery")
@Api(tags = "交易订单发货表管理")
@Module(code = "listInvoice",parent = "transaction",name = "发货单列表",index = 7)
public class TradeDeliveryController {

    @DubboReference
    private ITradeDeliveryRpc TradeDeliveryRpc;

    @ApiOperation("交易订单发货表列表")
    @GetMapping("/list")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradeDeliveryVO.ListVO>> list(TradeDeliveryQTO.QTO qto) {
        return ResponseData.data(TradeDeliveryRpc.pageData(qto));
    }

    @ApiOperation("交易订单发货表详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<TradeDeliveryVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradeDeliveryRpc.detailTradeDelivery(new TradeDeliveryDTO.IdDTO(id)));
    }

    @ApiOperation("新增交易订单发货表")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@Valid @RequestBody TradeDeliveryDTO.ETO dto) {
            TradeDeliveryRpc.addTradeDelivery(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除交易订单发货表")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete",name = "删除")
    public ResponseData<Void> delete(@PathVariable String id) {
        TradeDeliveryDTO.IdDTO dto = new TradeDeliveryDTO.IdDTO(id);
        TradeDeliveryRpc.deleteTradeDelivery(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改交易订单发货表")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody TradeDeliveryDTO.ETO eto) {
        eto.setId(id);
        TradeDeliveryRpc.editTradeDelivery(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
