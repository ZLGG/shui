package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRankingRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2020-12-18
*/
@RestController
@RequestMapping("/platadmin/tradeRanking")
@Api(tags = "销售数据分析")
@Module(code = "salesAnalysis",parent = "report",name = "销售数据分析" ,index = 1)
public class TradeSalesAnalysisController {

    @DubboReference
    private ITradeRankingRpc iTradeRankingRpc;


    @ApiOperation("销售数据分析")
    @GetMapping("/salesList")
    @Func(code = "view" ,name = "查看")
    public ResponseData<TradeSettlementVO.SaleslVO> salesList(TradeSettlementQTO.SaleslQTO qto) {
        return ResponseData.data(iTradeRankingRpc.salesList(qto));
    }



}
