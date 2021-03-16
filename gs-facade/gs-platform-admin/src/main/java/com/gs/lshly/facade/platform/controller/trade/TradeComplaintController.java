package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeComplaintDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeComplaintQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeComplaintVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeComplaintRpc;
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
* @author Starry
* @since 2020-12-24
*/
@RestController
@RequestMapping("/platadmin/tradeComplaint")
@Api(tags = "订单投诉管理管理")
@Module(code = "orderComplaints",parent = "transaction",name = "订单投诉",index = 10)
public class TradeComplaintController {

    @DubboReference
    private ITradeComplaintRpc TradeComplaintRpc;

    @ApiOperation("订单投诉管理列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradeComplaintVO.DetailListVO>> list(TradeComplaintQTO.QTO qto) {
        return ResponseData.data(TradeComplaintRpc.pageData(qto));
    }

    @ApiOperation("删除订单投诉管理")
    @PostMapping(value = "/deleteBatch")
    @Func(code = "delete",name = "删除")
    public ResponseData<Void> delete(@RequestBody TradeComplaintDTO.IdListDTO dto) {
        TradeComplaintRpc.deleteTradeComplaint(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("订单投诉管理详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<TradeComplaintVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradeComplaintRpc.detailTradeComplaint(new TradeComplaintDTO.IdDTO(id)));
    }

    @ApiOperation("提交订单投诉管理")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody TradeComplaintDTO.ETO eto) {
        eto.setId(id);
        TradeComplaintRpc.editTradeComplaint(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
