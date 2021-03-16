package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsRpc;
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
* @since 2020-12-06
*/
@RestController
@RequestMapping("/bbc/userCenter/tradeRights")
@Api(tags = "交易售后表管理")
public class BbcTradeRightsController {

    @DubboReference
    private IBbcTradeRightsRpc bbcTradeRightsRpc;

    @ApiOperation("交易售后表列表")
    @GetMapping("")
    public ResponseData<PageData<BbcTradeRightsVO.ListVO>> list(BbcTradeRightsQTO.RightsList qto) {
        return ResponseData.data(bbcTradeRightsRpc.tradeRightsListData(qto));
    }

    @ApiOperation("交易售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbcTradeRightsVO.DetailVO> get(BbcTradeRightsDTO.IdDTO id) {
        return ResponseData.data(bbcTradeRightsRpc.detailTradeRights(id));
    }

    @ApiOperation("申请售后")
    @PostMapping("/apply")
    public ResponseData<Void> add(@Valid @RequestBody BbcTradeRightsBuildDTO.ETO dto) {
            bbcTradeRightsRpc.addTradeRights(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("货物寄回")
    @PostMapping("/returnGoods")
    public ResponseData<Void> returnGoods(@Valid @RequestBody BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto) {
        bbcTradeRightsRpc.returnGoods(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
    @ApiOperation("确认收货")
    @GetMapping("/addAddress")
    public ResponseData<Void> confirmReceipt(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsRpc.confirmReceipt(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

}
