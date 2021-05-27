package com.gs.lshly.facade.bbc.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesGoodsRpc;
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
@RequestMapping("/bbc/userCenter/userFavoritesGoods")
@Api(tags = "商品收藏管理")
public class BbcUserFavoritesGoodsController {

    @DubboReference
    private IBbcUserFavoritesGoodsRpc bbcUserFavoritesGoodsRpc;

    @ApiOperation("商品收藏列表-v1.1.0")
    @GetMapping("")
    public ResponseData<PageData<BbcUserFavoritesGoodsVO.ListVO>> list(BbcUserFavoritesGoodsQTO.QTO qto) {
        return ResponseData.data(bbcUserFavoritesGoodsRpc.pageData(qto));
    }

    @ApiOperation("商品收藏添加")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbcUserFavoritesGoodsDTO.ETO dto) {
        bbcUserFavoritesGoodsRpc.addUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("商品收藏移除")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbcUserFavoritesGoodsDTO.IdListDTO dto) {
        bbcUserFavoritesGoodsRpc.deleteBatchUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
