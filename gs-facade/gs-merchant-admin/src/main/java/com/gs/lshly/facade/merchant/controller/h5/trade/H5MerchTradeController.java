package com.gs.lshly.facade.merchant.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/h5/trade")
@Api(tags = "H5交易订单表管理")
public class H5MerchTradeController {

    @DubboReference
    private IH5MerchTradeRpc h5MerchTradeRpc;


    @ApiOperation("交易订单表列表")
    @GetMapping("/list")
    public ResponseData<PageData<H5MerchTradeListVO.tradeVO>> list(H5MerchTradeQTO.TradeList qto) {
        return ResponseData.data(h5MerchTradeRpc.tradeListPageData(qto));
    }

    @ApiOperation("交易订单表详情")
    @GetMapping(value = "/detail")
    public ResponseData<H5MerchTradeListVO.tradeVO> detail(H5MerchTradeDTO.IdDTO dto) {
        return ResponseData.data(h5MerchTradeRpc.detail(dto));
    }

    @ApiOperation("修改订单金额/修改运费")
    @GetMapping(value = "/editOrderAmount")
    public ResponseData<Void> editOrderAmount(H5MerchTradeDTO.orderAmountOrFreight dto) {
        h5MerchTradeRpc.editOrderAmount(dto);
        return ResponseData.data(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("提交备注")
    @GetMapping(value = "/editComment")
    public ResponseData<Void> editComment(H5MerchTradeDTO.editComment dto) {
        h5MerchTradeRpc.editComment(dto);
        return ResponseData.data(MsgConst.UPDATE_SUCCESS);
    }

}
