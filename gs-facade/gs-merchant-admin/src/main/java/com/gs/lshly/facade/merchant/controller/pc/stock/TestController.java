package com.gs.lshly.facade.merchant.controller.pc.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockDeliveryRpc;
import com.gs.lshly.rpc.api.common.IExpressSurviceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zzg
* @since 2020-10-30
*/
@RestController
@RequestMapping("/platadmin/test")
@Api(tags = "内部测试")
public class TestController {

    @DubboReference
    private IBbcStockDeliveryRpc iBbcStockDeliveryRpc;

    @DubboReference
    private IExpressSurviceRpc iExpressSurviceRpc;

    @DubboReference
    private IBbbPcStockAddressRpc iBbbPcStockAddressRpc;



    @ApiOperation("运费计算")
    @GetMapping("")
    public BbcStockDeliveryVO.DeliveryAmountVO test() {
        BbcStockDeliveryDTO.DeliveryAmountDTO dto=new BbcStockDeliveryDTO.DeliveryAmountDTO();
        dto.setDeliveryType(10);
        BbcStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO=new BbcStockDeliveryDTO.DeliverySKUDTO();
        deliverySKUDTO.setSkuId("1334434257765986306");
        deliverySKUDTO.setAmount(new BigDecimal(2));
        List<BbcStockDeliveryDTO.DeliverySKUDTO> list=new ArrayList<BbcStockDeliveryDTO.DeliverySKUDTO>();
        list.add(deliverySKUDTO);
        dto.setSkus(list);
        dto.setAddressId("1329711918228516866");
        BbcStockDeliveryVO.DeliveryAmountVO vo=iBbcStockDeliveryRpc.calculate(dto);
        return vo;
    }

    @ApiOperation("快递鸟查询物流")
    @GetMapping("/queryExpress/{expressNumber}")
    public ResponseData<LogisticsInformationVO.ListVO> queryExpress(StockKdniaoDTO.TradeTailDTO dto) {
        return ResponseData.data(iExpressSurviceRpc.orderLogisticsInformation(dto));
    }


    @ApiOperation("查询配送地区")
    @GetMapping("/queryTemplateAddress/{id}")
    public ResponseData<String[]> queryTemplateAddress(@PathVariable String id) {
        return ResponseData.data(iBbbPcStockAddressRpc.queryStockAddress(id));
    }
}
