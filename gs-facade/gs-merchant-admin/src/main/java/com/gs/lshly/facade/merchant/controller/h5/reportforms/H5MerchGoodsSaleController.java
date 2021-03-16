package com.gs.lshly.facade.merchant.controller.h5.reportforms;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeGoodsRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author zst
 * @version 1.0
 * @date 2021/1/16 16:40
 */
@RestController
@RequestMapping("/merchadmin/H5/report")
@Api(tags = "H5数据管理")
public class H5MerchGoodsSaleController {

    @DubboReference
    private IPCMerchTradeGoodsRpc ipcMerchTradeGoodsRpc;

    @DubboReference
    private IPCMerchTradeRpc ipcMerchTradeRpc;

    @ApiOperation("销售概括")
    @GetMapping("/salesSummaryList")
    public ResponseData<TradeVO.SalesSummaryVO> salesSummaryList(BaseDTO dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.salesSummaryList(dto));
    }

    @ApiOperation("店铺绩效")
    @GetMapping("/performanceList")
    public ResponseData<TradeVO.PerformanceVO> performanceList(TradeDTO.PerformanceListDTO dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.performanceList(dto));
    }

    @ApiOperation("客户统计")
    @GetMapping("/clientList")
    public ResponseData<TradeVO.ClientListVO> clientList(TradeDTO.ClientListDTO dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.clientList(dto));
    }

    @ApiOperation("商品统计")
    @GetMapping("/goodsDateList")
    public ResponseData<TradeVO.GoodsDateVO> goodsDateList(TradeDTO.PerformanceListDTO dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.goodsDateList(dto));
    }
}
