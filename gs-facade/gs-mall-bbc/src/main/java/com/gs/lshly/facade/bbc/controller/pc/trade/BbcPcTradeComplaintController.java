package com.gs.lshly.facade.bbc.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeComplaintRpc;
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
@RequestMapping("/bbc/pc/tradeComplaint")
@Api(tags = "2C PC订单投诉管理管理")
public class BbcPcTradeComplaintController {

    @DubboReference
    private IPCBbbTradeComplaintRpc pcBbbTradeComplaintRpc;

    @ApiOperation("订单投诉管理列表")
    @GetMapping("")
    public ResponseData<PageData<PCBbbTradeComplaintVO.DetailListVO>> list(PCBbbTradeComplaintQTO.QTO qto) {
        return ResponseData.data(pcBbbTradeComplaintRpc.pageData(qto));
    }

    @ApiOperation("订单投诉管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCBbbTradeComplaintVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcBbbTradeComplaintRpc.detailTradeComplaint(new PCBbbTradeComplaintDTO.IdDTO(id)));
    }

    @ApiOperation("新增订单投诉管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCBbbTradeComplaintDTO.DetailEto eto) {
            pcBbbTradeComplaintRpc.addTradeComplaint(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("撤销订单投诉管理")
    @PostMapping(value = "/cancelComplaint")
    public ResponseData<Void> update(@RequestBody PCBbbTradeComplaintDTO.CancelIdeaDTO dto) {
        pcBbbTradeComplaintRpc.editTradeComplaint(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
