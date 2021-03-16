package com.gs.lshly.facade.merchant.controller.pc.reportforms;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeGoodsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeGoodsRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author zst
 * @version 1.0
 * @date 2021/1/4 15:40
 */
@RestController
@RequestMapping("/merchadmin/report")
@Api(tags = "商家报表")
public class PCMerchGoodsSaleController {

    @DubboReference
    private IPCMerchTradeGoodsRpc ipcMerchTradeGoodsRpc;

    @DubboReference
    private IPCMerchTradeRpc ipcMerchTradeRpc;

    @ApiOperation("商品销售分析列表")
    @GetMapping("/goodsSaleList")
    public ResponseData<List<TradeGoodsVO.GoodsSaleVO>> goodsSaleList(TradeGoodsDTO.GoodsSale dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.goodsSaleList(dto));
    }

    @ApiOperation("商品销售排行明细")
    @GetMapping("/goodsSaleListDetail")
    public ResponseData<List<TradeGoodsVO.GoodsSaleVO>> goodsSaleListDetail(TradeGoodsDTO.GoodsSale dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.goodsSaleListDetail(dto));
    }

    @ApiOperation("交易数据分析")
    @GetMapping("/payDatelist")
    public ResponseData<TradeVO.PayDatelistVO> payDateList(TradeDTO.PayDateList dto) {
        return ResponseData.data(ipcMerchTradeRpc.payDateList(dto));
    }

    @ApiOperation("商家运营概括")
    @GetMapping("/operationList")
    public ResponseData<TradeVO.OperationlistVO> operationList(TradeDTO.OperationList dto) {
        return ResponseData.data(ipcMerchTradeRpc.operationList(dto));
    }

}
