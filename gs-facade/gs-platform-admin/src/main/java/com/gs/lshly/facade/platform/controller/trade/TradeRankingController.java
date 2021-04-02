package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRankingRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

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
@Api(tags = "商品点击排行分析")
@Module(code = "rankingAnalysis",parent = "report",name = "商品点击排行分析" ,index = 2)
public class TradeRankingController {

    @DubboReference
    private ITradeRankingRpc iTradeRankingRpc;

    @ApiOperation("商品点击排行分析")
    @GetMapping("/list")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeRankingVO.RankingVO>> list(TradeRankingQTO.RankingQTO qto) {
        return ResponseData.data(iTradeRankingRpc.pageData(qto));
    }


    @ApiOperation("导出商品点击排行分析")
    @Log(module = "商品点击排行分析", func = "导出商品点击排行分析")
    @GetMapping(value = "/export")
    public void export(TradeRankingQTO.RankingQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = iTradeRankingRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

}
