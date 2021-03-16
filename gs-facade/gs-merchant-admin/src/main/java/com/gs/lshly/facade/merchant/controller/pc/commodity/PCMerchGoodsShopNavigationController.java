package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsQaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsShopNavigationDTO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsShopNavigationRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Starry
 * @Date 14:18 2020/12/7
 */
@RestController
@Api(tags = "商品与店铺类目关系管理")
@RequestMapping("/merchant/pc/goodsShopNavigation")
public class PCMerchGoodsShopNavigationController {

    @DubboReference
    private IPCMerchGoodsShopNavigationRpc goodsShopNavigationRpc;

    @ApiOperation("关联商品")
    @PostMapping(value = "bindGoods")
    public ResponseData<Void> bindGoods(@RequestBody PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto) {
        goodsShopNavigationRpc.bindGoods(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
}
