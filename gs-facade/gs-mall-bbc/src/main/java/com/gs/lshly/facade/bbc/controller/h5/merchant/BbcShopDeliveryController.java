package com.gs.lshly.facade.bbc.controller.h5.merchant;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockDeliveryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* <p>
*  物流运费查询前端控制器
* </p>
*
* @author lxus
* @since 2020-11-02
*/
@RestController
@RequestMapping("/bbc/stockDelivery")
@Api(tags = "门店物流、运费查询")
public class BbcShopDeliveryController {

    @DubboReference
    private IBbcStockDeliveryRpc stockDeliveryRpc;

    @DubboReference
    private IBbcShopRpc shopRpc;

    @ApiOperation("店铺支持的配送方式")
    @GetMapping("/{shopId}")
    public ResponseData<BbcStockDeliveryVO.SupportDeliveryTypeVO> list(@PathVariable String shopId) {
        BbcStockDeliveryDTO.SupportDeliveryTypeDTO dto = new BbcStockDeliveryDTO.SupportDeliveryTypeDTO();
        dto.setShopId(shopId);
        return ResponseData.data(shopRpc.supportDeliveryStyle(dto));
    }

    @ApiOperation("计算运费(前端可交给后端调用)")
    @PostMapping(value = "/calculate")
    public ResponseData<BbcStockDeliveryVO.DeliveryAmountVO> deliveryAmount(@Valid @RequestBody BbcStockDeliveryDTO.DeliveryAmountDTO dto) {
        return ResponseData.data(stockDeliveryRpc.calculate(dto));
    }


}
