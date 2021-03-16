package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesShopRpc;
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
@RequestMapping("/bbb/userCenter/favoriteShop")
@Api(tags = "店铺收藏管理",description = " ")
public class PCBbbUserFavoriteShopController {

    @DubboReference
    private IBbbUserFavoritesShopRpc bbbUserFavoritesShopRpc;


    @ApiOperation("店铺收藏列表")
    @GetMapping("")
    public ResponseData<PageData<BbbUserFavoritesShopVO.ListVO>> list(BbbUserFavoritesShopQTO.QTO qto) {
        return ResponseData.data(bbbUserFavoritesShopRpc.pageData(qto));
    }

    @ApiOperation("收藏店铺")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbUserFavoritesShopDTO.ETO dto) {
        bbbUserFavoritesShopRpc.addUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("取消收藏店铺")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbUserFavoritesShopDTO.IdListDTO dto) {
        bbbUserFavoritesShopRpc.deleteBatchUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
