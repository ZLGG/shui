package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserShoppingCarRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserShoppingCarRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-28
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/userShoppingCar")
@Api(tags = "2C PC会员购物车管理")
public class BbcPcUserShoppingCarController {

    @DubboReference
    private IBbbUserShoppingCarRpc bbbUserShoppingCarRpc;

    @DubboReference
    private IBbcUserShoppingCarRpc bbcUserShoppingCarRpc;

    @ApiOperation("购物车商品列表")
    @GetMapping("")
    public ResponseData<List<BbbUserShoppingCarVO.ListVO>> list(BbbUserShoppingCarQTO.QTO qto) {
        return ResponseData.data(bbbUserShoppingCarRpc.list(qto));
    }

    @ApiOperation("购物车项数量统计(角标数字)")
    @GetMapping("/count")
    public ResponseData<BbbUserShoppingCarVO.CountVO> countShoppingCarGoods() {
        return ResponseData.data(bbbUserShoppingCarRpc.countShoppingCarGoods(new BaseDTO()));
    }

//    @ApiOperation("新增购物车商品")
//    @PostMapping("")
//    public ResponseData<Void> add(@Valid @RequestBody BbbUserShoppingCarDTO.ETO dto) {
//        bbbUserShoppingCarRpc.addUserShoppingCar(dto);
//        return ResponseData.success(MsgConst.ADD_SUCCESS);
//    }

    @ApiOperation("新增购物车商品(实现二)")
    @PostMapping("/add")
    public ResponseData<Void> addShopping(@Valid @RequestBody BbcUserShoppingCarDTO.ETO dto) {
        bbcUserShoppingCarRpc.addUserShoppingCar(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除购物车商品")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbcUserShoppingCarDTO.IdListDTO dto) {
        bbcUserShoppingCarRpc.deleteBatchUserShoppingCar(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("改变购物车商品选中状态(ID是购物车ID)")
    @PutMapping(value = "/selectState/{id}")
    public ResponseData<Void> selectState(@PathVariable String id) {
        BbcUserShoppingCarDTO.SelectDTO eto = new BbcUserShoppingCarDTO.SelectDTO(id);
        bbcUserShoppingCarRpc.selectState(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品选中状态（全选/返选）")
    @PutMapping(value = "/selectStateAll")
    public ResponseData<Void> selectStateAll(@Valid @RequestBody BbcUserShoppingCarDTO.SelectAllDTO dto) {
        bbcUserShoppingCarRpc.selectStateAll(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品数量(ID是购物车ID)")
    @PutMapping(value = "/{id}")
    public ResponseData<BbbUserShoppingCarVO.ChangeQuantityVO> updateQuantity(@PathVariable String id, @Valid @RequestBody BbbUserShoppingCarDTO.QuantityDTO eto) {
        eto.setId(id);
        return ResponseData.data(bbbUserShoppingCarRpc.changeQuantity(eto));
    }

    @ApiOperation("获取阶梯价(SKU-ID)")
    @GetMapping(value = "/getGoodsStepPrice/{skuId}")
    public ResponseData<PCBbbGoodsInfoVO.GetGoodsStepPriceVO> getGradePrice(@PathVariable String skuId) {
        return ResponseData.data(bbbUserShoppingCarRpc.getGoodsStepPrice(new BbbUserShoppingCarDTO.QueryGradePriceDTO(skuId)));
    }
}
