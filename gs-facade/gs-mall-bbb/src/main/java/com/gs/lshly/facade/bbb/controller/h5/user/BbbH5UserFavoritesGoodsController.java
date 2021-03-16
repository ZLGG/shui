package com.gs.lshly.facade.bbb.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesGoodsQTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesGoodsRpc;
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
* @author xxfc
* @since 2020-10-28
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/userFavoritesGoods")
@Api(tags = "商品收藏管理")
public class BbbH5UserFavoritesGoodsController {

    @DubboReference
    private IBbbH5UserFavoritesGoodsRpc bbbH5UserFavoritesGoodsRpc;

    @ApiOperation("商品收藏列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO>> list(BbbH5UserFavoritesGoodsQTO.QTO qto) {
        return ResponseData.data(bbbH5UserFavoritesGoodsRpc.pageData(qto));
    }

    @ApiOperation("商品收藏添加")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbH5UserFavoritesGoodsDTO.ETO dto) {
        bbbH5UserFavoritesGoodsRpc.addUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("商品收藏移除")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbH5UserFavoritesGoodsDTO.IdListDTO dto) {
        bbbH5UserFavoritesGoodsRpc.deleteBatchUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
