package com.gs.lshly.facade.bbc.controller.pc.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.rpc.api.common.IExpressSurviceRpc;
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
* @author zst
* @since 2021-1-30
*/
@RestController
@RequestMapping("/bbc/pc/stockKdniao")
@Api(tags = "快递鸟物流公司代码管理")
public class BbcPcStockKdniaoController {

    @DubboReference
    private IExpressSurviceRpc iExpressSurviceRpc;

    @ApiOperation("快递鸟查询物流")
    @GetMapping("/queryExpress/{expressNumber}")
    public ResponseData<LogisticsInformationVO.ListVO> queryExpress(StockKdniaoDTO.TradeTailDTO dto) {
        return ResponseData.data(iExpressSurviceRpc.orderLogisticsInformation(dto));
    }

}
