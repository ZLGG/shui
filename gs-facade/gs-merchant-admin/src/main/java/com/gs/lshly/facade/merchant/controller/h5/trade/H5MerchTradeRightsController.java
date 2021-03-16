package com.gs.lshly.facade.merchant.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeRightsRpc;
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
@RequestMapping("/merchadmin/h5/tradeRights")
@Api(tags = "H5商家售后表管理")
public class H5MerchTradeRightsController {

    @DubboReference
    private IH5MerchTradeRightsRpc ih5MerchTradeRightsRpc;

    @ApiOperation("退换货管理")
    @PostMapping("")
    public ResponseData<PageData<H5MerchTradeRightsVO.RightList>> list(@Valid @RequestBody H5MerchTradeRightsQTO.QTO qto) {
        return ResponseData.data(ih5MerchTradeRightsRpc.pageData(qto));
    }

    @ApiOperation("商家售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<H5MerchTradeRightsVO.DetailVO> get(H5MerchTradeRightsDTO.IdDTO qto) {
        return ResponseData.data(ih5MerchTradeRightsRpc.detailTradeRights(qto));
    }
    @ApiOperation("审核")
    @PostMapping(value = "/check")
    public ResponseData<Void> check(@Valid @RequestBody H5MerchTradeRightsDTO.IdCheckDTO dto) {
        ih5MerchTradeRightsRpc.check(dto);
        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }

    @ApiOperation("收到退货")
    @GetMapping(value = "/received/{id}")
    public ResponseData<Void> receivedGet(H5MerchTradeRightsDTO.IdDTO qto) {
        ih5MerchTradeRightsRpc.receivedGet(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
    @ApiOperation("寄回用户")
    @PostMapping(value = "/send/{id}")
    public ResponseData<Void> send(@Valid @RequestBody H5MerchTradeRightsDTO.SendDTO qto) {
        ih5MerchTradeRightsRpc.send(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
