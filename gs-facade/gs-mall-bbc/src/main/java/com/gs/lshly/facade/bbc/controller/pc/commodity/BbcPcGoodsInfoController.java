package com.gs.lshly.facade.bbc.controller.pc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Starry
 * @Date 10:38 2020/11/27
 */
@RestController
@RequestMapping("/bbc/pc/goodsInfo")
@Api(tags = "2C PC端商品信息管理")
public class BbcPcGoodsInfoController {

    @DubboReference
    private IPCBbbGoodsInfoRpc goodsInfoRpc;

    @ApiOperation("商品列表")
    @PostMapping("")
    public ResponseData<PageData<PCBbbGoodsInfoVO.GoodsDetailListVO>> pageDataResponseData(@RequestBody PCBbbGoodsInfoQTO.GoodsSearchQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageGoodsDetailVO(qto));
    }

    @ApiOperation("商品详情")
    @GetMapping("{id}")
    public ResponseData<PCBbbGoodsInfoVO.GoodsDetailVO> getGoodsDetailVO(@PathVariable String id) {
        PCBbbGoodsInfoDTO.IdDTO dto = new PCBbbGoodsInfoDTO.IdDTO(id);
        return ResponseData.data(goodsInfoRpc.getGoodsDetailVO(dto));
    }

    @ApiOperation("商品搜索列表")
    @GetMapping("/searchGoods")
    public ResponseData<PageData<PCBbbGoodsInfoVO.GoodsListVO>> getGoodsListVO(PCBbbGoodsInfoQTO.SearchByGoodsNameQTO qto) {
        return ResponseData.data(goodsInfoRpc.getSearchGoods(qto));
    }

    @ApiOperation("店铺自定义分类下面的商品列表")
    @GetMapping("/pageShopNavigationGoods")
    public ResponseData<PageData<PCBbbGoodsInfoVO.GoodsDetailListVO>> pageShopNavigationGoods(PCBbbGoodsInfoQTO.ShopNavigationIdQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageShopNavigationGoodsVO(qto));
    }
}
