package com.gs.lshly.facade.bbb.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeComplaintVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeComplaintRpc;
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
* @since 2020-12-23
*/
@RestController
@RequestMapping("/bbb/h5/tradeComplaint")
@Api(tags = "订单投诉管理管理")
public class BbbH5TradeComplaintController {

    @DubboReference
    private IBbbH5TradeComplaintRpc iBbbH5TradeComplaintRpc;

    @ApiOperation("订单投诉管理列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5TradeComplaintVO.DetailListVO>> list(BbbH5TradeComplaintQTO.QTO qto) {
        return ResponseData.data(iBbbH5TradeComplaintRpc.pageData(qto));
    }

    @ApiOperation("订单投诉管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbbH5TradeComplaintVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(iBbbH5TradeComplaintRpc.detailTradeComplaint(new BbbH5TradeComplaintDTO.IdDTO(id)));
    }

    @ApiOperation("新增订单投诉管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbH5TradeComplaintDTO.DetailEto eto) {
        iBbbH5TradeComplaintRpc.addTradeComplaint(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("撤销订单投诉管理")
    @PostMapping(value = "/cancelComplaint")
    public ResponseData<Void> update(@RequestBody BbbH5TradeComplaintDTO.CancelIdeaDTO dto) {
        iBbbH5TradeComplaintRpc.editTradeComplaint(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
