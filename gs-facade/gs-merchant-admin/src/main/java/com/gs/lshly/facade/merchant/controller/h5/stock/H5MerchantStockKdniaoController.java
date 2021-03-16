package com.gs.lshly.facade.merchant.controller.h5.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.rpc.api.common.IExpressSurviceRpc;
import com.gs.lshly.rpc.api.platadmin.stock.IStockKdniaoRpc;
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
* @since 2021-01-29
*/
@RestController
@RequestMapping("/merchadmin/h5/stockKdniao")
@Api(tags = "快递鸟查询物流")
public class H5MerchantStockKdniaoController {

    @DubboReference
    private IStockKdniaoRpc StockKdniaoRpc;
    @DubboReference
    private IExpressSurviceRpc iExpressSurviceRpc;


    @ApiOperation("快递鸟查询物流")
    @GetMapping("/queryExpress/{expressNumber}")
    public ResponseData<LogisticsInformationVO.ListVO> queryExpress(StockKdniaoDTO.TradeTailDTO dto) {
        return ResponseData.data(iExpressSurviceRpc.orderLogisticsInformation(dto));
    }


//    @ApiOperation("顺丰查询")
//    @GetMapping("/queryUPS")
//    public ResponseData<LogisticsInformationVO.ListVO> queryUPS(StockKdniaoDTO.TradeTailDTO dto) {
//        return ResponseData.data(iExpressSurviceRpc.queryUPS(dto));
//    }

}
