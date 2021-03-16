package com.gs.lshly.facade.bbb.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbTradeRightsRpc;
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
@RequestMapping("/bbb/userCenter/tradeRights")
@Api(tags = "交易售后表管理")
public class BbbTradeRightsController {

    @DubboReference
    private IBbbTradeRightsRpc iBbbTradeRightsRpc;

    @ApiOperation("交易售后表列表")
    @GetMapping("")
    public ResponseData<PageData<BbbTradeRightsVO.ListVO>> list(BbbTradeRightsQTO.RightsList qto) {
        return ResponseData.data(iBbbTradeRightsRpc.tradeRightsListData(qto));
    }

    @ApiOperation("交易售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbbTradeRightsVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(iBbbTradeRightsRpc.detailTradeRights(new BbbTradeRightsDTO.IdDTO(id)));
    }

    @ApiOperation("申请售后")
    @PostMapping("/apply")
    public ResponseData<Void> add(@Valid @RequestBody BbbTradeRightsBuildDTO.ETO dto) {
        iBbbTradeRightsRpc.addTradeRights(dto);
        return ResponseData.success(MsgConst.APPLICATION_SSUCCESS);
    }
    @ApiOperation("添加寄回商家信息")
    @PostMapping("/addAddress")
    public ResponseData<Void> addAddress(@Valid @RequestBody BbbTradeRightsBuildDTO.AddAddressDTO dto) {
        iBbbTradeRightsRpc.addAddress(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
    @ApiOperation("确认收货")
    @GetMapping("/addAddress")
    public ResponseData<Void> confirmReceipt(BbbTradeRightsDTO.IdDTO dto) {
        iBbbTradeRightsRpc.confirmReceipt(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("再次申请售后")
    @PostMapping("/applyAgain")
    public ResponseData<Void> applyAgain(@Valid @RequestBody BbbTradeRightsBuildDTO.ApplyAgain dto) {
        iBbbTradeRightsRpc.applyAgain(dto);
        return ResponseData.success(MsgConst.APPLICATION_SSUCCESS);
    }


}
