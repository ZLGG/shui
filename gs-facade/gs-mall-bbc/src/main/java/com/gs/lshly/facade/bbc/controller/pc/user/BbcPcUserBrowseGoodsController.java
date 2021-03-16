package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/browseGoods")
@Api(tags = "2C PC会员最近浏览商品")
public class BbcPcUserBrowseGoodsController {

    @DubboReference
    private IBbbUserFavoritesShopRpc bbbUserFavoritesShopRpc;


    @ApiOperation("我绑定的私域店铺")
    @GetMapping("")
    public ResponseData<PageData<BbbUserFavoritesShopVO.ListVO>> list(BbbUserFavoritesShopQTO.QTO qto) {
        return ResponseData.data(bbbUserFavoritesShopRpc.pageData(qto));
    }




}
