package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesShopRpc;
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
* @since 2020-10-29
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/userFavoritesShop")
@Api(tags = "店铺收藏管理")
public class BbbH5UserFavoritesShopController {

    @DubboReference
    private IBbbH5UserFavoritesShopRpc bbbH5UserFavoritesShopRpc;

    @ApiOperation("店铺收藏列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5UserFavoritesShopVO.ListVO>> list(BbbH5UserFavoritesShopQTO.QTO qto) {
        return ResponseData.data(bbbH5UserFavoritesShopRpc.pageData(qto));
    }

    @ApiOperation("收藏店铺")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbbH5UserFavoritesShopDTO.ETO dto) {
        bbbH5UserFavoritesShopRpc.addUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("取消收藏店铺")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbH5UserFavoritesShopDTO.IdListDTO dto) {
        bbbH5UserFavoritesShopRpc.deleteBatchUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
