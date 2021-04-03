package com.gs.lshly.facade.bbb.controller.h5.trade;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRightsRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author zdf
* @since 2021-01-04
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/tradeRights")
@Api(tags = "交易售后表管理")
public class BbbH5TradeRightsController {

    @DubboReference
    private IBbbH5TradeRightsRpc iBbbH5TradeRightsRpc;

    @ApiOperation("交易售后表列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5TradeRightsVO.ListVO>> list(BbbH5TradeRightsQTO.RightsList qto) {
        return ResponseData.data(iBbbH5TradeRightsRpc.tradeRightsListData(qto));
    }

    @ApiOperation("交易售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbbH5TradeRightsVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(iBbbH5TradeRightsRpc.detailTradeRights(new BbbH5TradeRightsDTO.IdDTO(id)));
    }

    @ApiOperation("申请售后")
    @PostMapping("/apply")
    public ResponseData<Void> add(@Valid @RequestBody BbbH5TradeRightsBuildDTO.ETO dto) {
        iBbbH5TradeRightsRpc.addTradeRights(dto);
        return ResponseData.success(MsgConst.APPLICATION_SSUCCESS);
    }
    @ApiOperation("添加寄回商家信息")
    @PostMapping("/addAddress")
    public ResponseData<Void> addAddress(@Valid @RequestBody BbbH5TradeRightsBuildDTO.AddAddressDTO dto) {
        iBbbH5TradeRightsRpc.addAddress(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
    @ApiOperation("确认收货")
    @GetMapping("/addAddress")
    public ResponseData<Void> confirmReceipt(BbbH5TradeRightsDTO.IdDTO dto) {
        iBbbH5TradeRightsRpc.confirmReceipt(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
    @ApiOperation("再次申请售后")
    @PostMapping("/applyAgain")
    public ResponseData<Void> applyAgain(@Valid @RequestBody BbbH5TradeRightsBuildDTO.ApplyAgain dto) {
        iBbbH5TradeRightsRpc.applyAgain(dto);
        return ResponseData.success(MsgConst.APPLICATION_SSUCCESS);
    }


}
