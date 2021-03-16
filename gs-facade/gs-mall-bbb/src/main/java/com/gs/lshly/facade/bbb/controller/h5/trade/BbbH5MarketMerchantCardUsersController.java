package com.gs.lshly.facade.bbb.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketMerchantCardUsersRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
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
