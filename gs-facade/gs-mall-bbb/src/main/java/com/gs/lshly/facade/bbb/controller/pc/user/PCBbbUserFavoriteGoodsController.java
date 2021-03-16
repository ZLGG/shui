package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesGoodsVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesGoodsRpc;
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
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbb/userCenter/favoriteGoods")
@Api(tags = "商品收藏管理",description = " ")
public class PCBbbUserFavoriteGoodsController {

    @DubboReference
    private IBbbUserFavoritesGoodsRpc bbbUserFavoritesGoodsRpc;


    @ApiOperation("商品收藏列表")
    @GetMapping("")
    public ResponseData<PageData<BbbUserFavoritesGoodsVO.ListVO>> list(BbbUserFavoritesGoodsQTO.QTO qto) {
        return ResponseData.data(bbbUserFavoritesGoodsRpc.pageData(qto));
    }

    @ApiOperation("商品收藏添加")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbUserFavoritesGoodsDTO.ETO dto) {
        bbbUserFavoritesGoodsRpc.addUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("商品收藏移除")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbUserFavoritesGoodsDTO.IdListDTO dto) {
        bbbUserFavoritesGoodsRpc.deleteBatchUserFavoritesGoods(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


}
