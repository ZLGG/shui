package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsRefundDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRightsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsRefundVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsLogRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRightsRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zdf
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/platform/tradeRights")
@Api(tags = "平台退换货管理")
public class TradeRightsController {

    @DubboReference
    private ITradeRightsRpc iTradeRightsRpc;

    @ApiOperation("售后申请列表")
    @PostMapping("")
    public ResponseData<PageData<TradeRightsVO.RightsListVO>> list(@RequestBody TradeRightsQTO.StateDTO qto) {
        return ResponseData.data(iTradeRightsRpc.pageData(qto));
    }

    @ApiOperation("售后申请详情")
    @GetMapping(value = "/{id}")
    public ResponseData<TradeRightsVO.RightsListViewVO> get(TradeRightsDTO.IdDTO dto) {
        return ResponseData.data(iTradeRightsRpc.get(dto));
    }

/*    @ApiOperation("二次审核")
    @GetMapping(value = "/check/{id}")
    public ResponseData<String> twoCheck(TradeRightsDTO.IdDTO dto) {
        return ResponseData.data(iTradeRightsRpc.get(dto));
    }*/

    @ApiOperation("提交平台处理说明")
    @PostMapping(value = "/{id}")
    public ResponseData<String> setPlatformCheckReason(TradeRightsDTO.PlatformCheckReasonDTO dto) {
        iTradeRightsRpc.setPlatformChenkReason(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("平台审核通过")
    @GetMapping(value = "/pass/{id}")
    public ResponseData<String> platformPass(TradeRightsDTO.IdDTO dto) {
        iTradeRightsRpc.platformCheckReason(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("平台审核拒绝")
    @GetMapping(value = "/disPass/{id}")
    public ResponseData<String> platformDisPass(TradeRightsDTO.IdDTO dto) {
        iTradeRightsRpc.platformDisPass(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
/*    @ApiOperation("退款申请列表(无)")
    @GetMapping("/rightsList")
    public ResponseData<PageData<TradeRightsVO.RightsRefundListVO>> rightsRefundList(TradeRightsQTO.StateRefundDTO qto) {
        return ResponseData.data(iTradeRightsRpc.getRightsRefundList(qto));
    }

    @ApiOperation("退款申请详情(无)")
    @GetMapping(value = "/getRightsRefund/{id}")
    public ResponseData<TradeRightsVO.RightsRefundViewVO> getRightsRefund(TradeRightsDTO.IdDTO dto) {
        return ResponseData.data(iTradeRightsRpc.getRightsRefund(dto));
    }

    @ApiOperation("退款(无)")
    @PostMapping(value = "/getRightsRefundAmont/{id}")
    public ResponseData<Void> getRightsRefundAmont(@Valid @RequestBody TradeRightsDTO.RefundDTO dto) {
        iTradeRightsRpc.getRightsRefundAmont(dto);
        return ResponseData.success(MsgConst.REFUND_SUCCESS);
    }

    @ApiOperation("退款单列表")
    @PostMapping("/rightsRefunList")
    public ResponseData<PageData<TradeRightsRefundVO.DetailVO>> rightsRefunList(@Valid @RequestBody TradeRightsQTO.NewQTO qto) {
        return ResponseData.data(iTradeRightsRpc.rightsRefunList(qto));
    }

    @ApiOperation("退款单详情")
    @GetMapping("/rightsRefunView")
    public ResponseData<TradeRightsRefundVO.DetailViewVO> rightsRefunView(TradeRightsRefundDTO.IdDTO dto) {
        return ResponseData.data(iTradeRightsRpc.rightsRefunView(dto));
    }*/


}
