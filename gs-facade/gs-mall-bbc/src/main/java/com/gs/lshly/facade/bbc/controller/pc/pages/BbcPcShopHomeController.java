package com.gs.lshly.facade.bbc.controller.pc.pages;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbc/pc/shopHome")
@Api(tags = "2C PC端店铺首页管理")
public class BbcPcShopHomeController {

    @DubboReference
    private IBbbShopRpc bbbShopRpc;

    @ApiOperation("2C PC端店铺首页")
    @GetMapping("/{id}")
    public ResponseData<PCBbbShopHomeVO.ShopHomeVO> list(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.index(new BbbShopDTO.IdDTO(id)));
    }

    @ApiOperation("2C PC店铺楼层商品")
    @GetMapping("/floorGoods/{id}")
    public ResponseData<List<PCBbbShopHomeVO.FloorVO>> floorGoods(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.floorFirstList(new BbbShopDTO.IdDTO(id)));
    }

}
