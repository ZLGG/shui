package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserShoppingCarQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserShoppingCarRpc;
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
@RequestMapping("/bbb/h5/userCenter/userShoppingCar")
@Api(tags = "会员购物车管理")
public class BbbH5UserShoppingCarController {

    @DubboReference
    private IBbbH5UserShoppingCarRpc bbbH5UserShoppingCarRpc;

    @ApiOperation("购物车商品列表")
    @GetMapping("")
    public ResponseData<List<BbbH5UserShoppingCarVO.ListVO>> list(BbbH5UserShoppingCarQTO.QTO qto) {
        return ResponseData.data(bbbH5UserShoppingCarRpc.list(qto));
    }

    @ApiOperation("购物车项数量统计(角标数字)")
    @GetMapping("/count")
    public ResponseData<BbbH5UserShoppingCarVO.CountVO> countShoppingCarGoods() {
        return ResponseData.data(bbbH5UserShoppingCarRpc.countShoppingCarGoods(new BaseDTO()));
    }

    @ApiOperation("新增购物车商品")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbH5UserShoppingCarDTO.ETO dto) {
        bbbH5UserShoppingCarRpc.addUserShoppingCar(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除购物车商品")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbH5UserShoppingCarDTO.IdListDTO dto) {
        bbbH5UserShoppingCarRpc.deleteBatchUserShoppingCar(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("改变购物车商品选中状态")
    @PutMapping(value = "/selectState/{id}")
    public ResponseData<Void> selectState(@PathVariable String id) {
        BbbH5UserShoppingCarDTO.SelectDTO eto = new BbbH5UserShoppingCarDTO.SelectDTO(id);
        bbbH5UserShoppingCarRpc.selectState(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品选中状态（全选/返选）")
    @PutMapping(value = "/selectStateAll")
    public ResponseData<Void> selectStateAll(@Valid @RequestBody BbbH5UserShoppingCarDTO.SelectAllDTO dto) {
        bbbH5UserShoppingCarRpc.selectStateAll(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("改变购物车商品数量(ID是购物车ID)")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> updateQuantity(@PathVariable String id, @Valid @RequestBody BbbH5UserShoppingCarDTO.QuantityDTO eto) {
        eto.setId(id);
        bbbH5UserShoppingCarRpc.changeQuantity(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
