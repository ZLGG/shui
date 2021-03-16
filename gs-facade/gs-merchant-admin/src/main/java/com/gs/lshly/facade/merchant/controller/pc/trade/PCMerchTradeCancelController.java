package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCancelVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCancelRpc;
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
* @since 2020-11-20
*/
@RestController
@RequestMapping("/merchadmin/pc/tradeCancel")
@Api(tags = "交易订单取消表管理")
public class PCMerchTradeCancelController {

    @DubboReference
    private IPCMerchTradeCancelRpc pcMerchTradeCancelRpc;

    @ApiOperation("交易订单取消表列表")
    @PostMapping("/list")
    public ResponseData<PageData<PCMerchTradeCancelVO.ListVO>> list(@Valid @RequestBody PCMerchTradeCancelQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeCancelRpc.pageData(qto));
    }

    @ApiOperation("交易订单取消表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeCancelVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchTradeCancelRpc.detailTradeCancel(new PCMerchTradeCancelDTO.IdDTO(id)));
    }


    @ApiOperation("订单取消审核")
    @PostMapping(value = "/examine")
    public ResponseData<Void> update( @Valid @RequestBody PCMerchTradeCancelDTO.ExamineDTO eto) {

        return pcMerchTradeCancelRpc.examineTradeCancel(eto);
    }

}
