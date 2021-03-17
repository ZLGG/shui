package com.gs.lshly.facade.bbb.controller.h5.trade;

import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketActivityRpc;

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
@RequestMapping("/bbb/h5")
@Api(tags = "活动展示")
public class BbbH5MarkeActivityController {

    @DubboReference
    private IBbbH5MarketActivityRpc iBbbH5MarketActivityRpc;

    @ApiOperation("满减商品全部")
    @GetMapping("/marketActivity")
    public ResponseData<PageData<BbbH5MarketActivityVO.cutVO>> cutList(BbbH5MarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.cutList(qto));
    }
    @ApiOperation("满减商品显示4个")
    @GetMapping("/marketActivity/viewFour")
    public ResponseData<List<BbbH5MarketActivityVO.cutVO>> viewFour(BaseDTO dto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.viewFour(dto));
    }
    @ApiOperation("满折商品全部")
    @GetMapping("/marketActivity/discount")
    public ResponseData<PageData<BbbH5MarketActivityVO.discountVO>> discountList(BbbH5MarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.discountList(qto));
    }
    @ApiOperation("满折商品显示4个")
    @GetMapping("/marketActivity/discount/viewFour")
    public ResponseData<List<BbbH5MarketActivityVO.discountVO>> discountViewFour(BaseDTO dto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.discountViewFour(dto));
    }

    @ApiOperation("满赠商品全部")
    @GetMapping("/marketActivity/giftList")
    public ResponseData<PageData<BbbH5MarketActivityVO.giftVO>> giftList(BbbH5MarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.giftList(qto));
    }
    @ApiOperation("满赠商品显示4个")
    @GetMapping("/marketActivity/gift/viewFour")
    public ResponseData<List<BbbH5MarketActivityVO.giftVO>> giftViewFour(BaseDTO dto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.giftViewFour(dto));
    }

    @ApiOperation("团购商品全部")
    @GetMapping("/marketActivity/groupbuyList")
    public ResponseData<PageData<BbbH5MarketActivityVO.groupbuyVO>> groupbuyList(BbbH5MarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.groupbuyList(qto));
    }
    @ApiOperation("团购商品显示4个")
    @GetMapping("/marketActivity/groupbuy/viewFour")
    public ResponseData<List<BbbH5MarketActivityVO.groupbuyVO>> groupbuyViewFour(BaseDTO dto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.groupbuyViewFour(dto));
    }

    @ApiOperation("活动")
    @GetMapping("/marketActivity/activity")
    public ResponseData<BbbH5MarketActivityVO.activityVO> activity(BbbH5MarketActivityQTO.IdQTO qto) {
        return ResponseData.data(iBbbH5MarketActivityRpc.activity(qto));
    }

    @ApiOperation("活动(商品详情)")
    @PostMapping("/marketActivity/activityDetail")
    public ResponseData<BbbH5MarketActivityVO.ListActivityVO> activityList(@Valid @RequestBody BbbH5MarketMerchantActivityDTO.IdDTO dto) {

        return iBbbH5MarketActivityRpc.activityList(dto);
    }

    @ApiOperation("商家优惠卷")
    @PostMapping("/marketActivity/activityCard")
    public ResponseData<List<BbbH5MarketActivityVO.merchantCard>> merchantCard(@Valid @RequestBody BbbH5MarketMerchantActivityDTO.MerchantIdDTO dto) {

        return ResponseData.data(iBbbH5MarketActivityRpc.merchantCard(dto));
    }

    @ApiOperation("用户领取")
    @PostMapping("/userCenter/marketActivity/activityCard")
    public ResponseData<BbbH5MarketActivityVO.merchantCardSuccess> userReciveCard(@Valid @RequestBody BbbH5MarketMerchantActivityDTO.CardIdDTO dto) {

        return ResponseData.data(iBbbH5MarketActivityRpc.userReciveCard(dto));
    }

    @ApiOperation("活动权限控制")
    @PostMapping("/activity/jurisdiction")
    public ResponseData<BbbH5MarketActivityVO.jurisdiction> jurisdiction() {

        return ResponseData.data(iBbbH5MarketActivityRpc.jurisdiction());
    }



}
