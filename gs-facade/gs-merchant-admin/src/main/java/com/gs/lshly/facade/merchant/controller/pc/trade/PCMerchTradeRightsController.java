package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsRpc;
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
* @since 2020-12-17
*/
@RestController
@RequestMapping("/merchadmin/tradeRights")
@Api(tags = "2b商家售后表管理")
public class PCMerchTradeRightsController {

    @DubboReference
    private IPCMerchTradeRightsRpc pcMerchTradeRightsRpc;

    @ApiOperation("商家售后表")
    @PostMapping("")
    public ResponseData<PageData<PCMerchTradeRightsVO.RightList>> list(@Valid @RequestBody PCMerchTradeRightsQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeRightsRpc.pageData(qto));
    }

    @ApiOperation("商家售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeRightsVO.DetailVO> get(PCMerchTradeRightsDTO.IdDTO qto) {
        return ResponseData.data(pcMerchTradeRightsRpc.detailTradeRights(qto));
    }

    @ApiOperation("处理售后")
    @PostMapping(value = "/check")
    public ResponseData<Void> check(@Valid @RequestBody PCMerchTradeRightsDTO.IdCheckDTO dto) {
        pcMerchTradeRightsRpc.check(dto);
        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }

/*

    @ApiOperation("收到退货")
    @GetMapping(value = "/received/{id}")
    public ResponseData<Void> receivedGet(PCMerchTradeRightsDTO.IdDTO qto) {
        pcMerchTradeRightsRpc.receivedGet(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
    @ApiOperation("寄回用户")
    @PostMapping(value = "/send/{id}")
    public ResponseData<Void> send(@Valid @RequestBody PCMerchTradeRightsDTO.SendDTO qto) {
        pcMerchTradeRightsRpc.send(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }*/

}
