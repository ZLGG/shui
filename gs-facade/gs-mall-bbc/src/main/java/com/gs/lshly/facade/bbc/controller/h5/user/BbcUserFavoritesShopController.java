package com.gs.lshly.facade.bbc.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesShopRpc;
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
@RequestMapping("/bbc/userCenter/userFavoritesShop")
@Api(tags = "店铺收藏管理")
public class BbcUserFavoritesShopController {

    @DubboReference
    private IBbcUserFavoritesShopRpc bbcUserFavoritesShopRpc;

    @ApiOperation("店铺收藏列表")
    @GetMapping("")
    public ResponseData<PageData<BbcUserFavoritesShopVO.ListVO>> list(BbcUserFavoritesShopQTO.QTO qto) {
        return ResponseData.data(bbcUserFavoritesShopRpc.pageData(qto));
    }

    @ApiOperation("收藏店铺")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody BbcUserFavoritesShopDTO.ETO dto) {
        bbcUserFavoritesShopRpc.addUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("取消收藏店铺")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbcUserFavoritesShopDTO.IdListDTO dto) {
        bbcUserFavoritesShopRpc.deleteBatchUserFavoritesShop(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
