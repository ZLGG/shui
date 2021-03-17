package com.gs.lshly.facade.bbb.controller.h5.trade;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketMerchantCardUsersRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author zdf
* @since 2021-01-08
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/marketMerchantCardUsers")
@Api(tags = "我的优惠卷")
public class BbbH5MarketMerchantCardUsersController {

    @DubboReference
    private IBbbH5MarketMerchantCardUsersRpc iBbbH5MarketMerchantCardUsersRpc;

    @ApiOperation("我的优惠卷列表")
    @PostMapping("")
    public ResponseData<PageData<BbbH5MarketMerchantCardUsersVO.PageData>> list(@RequestBody BbbH5MarketMerchantCardUsersQTO.QTO qto) {
        return ResponseData.data(iBbbH5MarketMerchantCardUsersRpc.pageData(qto));
    }

}
