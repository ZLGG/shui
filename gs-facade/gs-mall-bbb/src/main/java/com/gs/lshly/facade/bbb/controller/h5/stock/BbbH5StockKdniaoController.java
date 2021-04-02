package com.gs.lshly.facade.bbb.controller.h5.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.rpc.api.common.IExpressSurviceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2021-1-30
*/
@RestController
@RequestMapping("/bbb/h5/stockKdniao")
@Api(tags = "快递鸟物流公司代码管理")
public class BbbH5StockKdniaoController {

    @DubboReference
    private IExpressSurviceRpc iExpressSurviceRpc;

    @ApiOperation("快递鸟查询物流")
    @GetMapping("/queryExpress/{expressNumber}")
    public ResponseData<LogisticsInformationVO.ListVO> queryExpress(StockKdniaoDTO.TradeTailDTO dto) {
        return ResponseData.data(iExpressSurviceRpc.orderLogisticsInformation(dto));
    }

    @ApiOperation("在途监控")
    @GetMapping("/onLineMonitoring")
    public ResponseData<LogisticsInformationVO.ListVO> onLineMonitoring(StockKdniaoDTO.TradeTailDTO dto) {
        return ResponseData.data(iExpressSurviceRpc.onLineMonitoring(dto));
    }
}
