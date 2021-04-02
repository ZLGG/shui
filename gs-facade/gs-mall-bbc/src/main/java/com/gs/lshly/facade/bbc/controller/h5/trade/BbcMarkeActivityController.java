package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketActivityRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zdf
* @since 2021-01-08
*/
@RestController
@RequestMapping("/bbc/h5")
@Api(tags = "活动展示")
public class BbcMarkeActivityController {

    @DubboReference
    private IBbcMarketActivityRpc iBbcMarketActivityRpc;

    @ApiOperation("满减商品全部")
    @GetMapping("/marketActivity")
    public ResponseData<PageData<BbcMarketActivityVO.cutVO>> cutList(BbcMarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.cutList(qto));
    }
    @ApiOperation("满减商品显示4个")
    @GetMapping("/marketActivity/viewFour")
    public ResponseData<List<BbcMarketActivityVO.cutVO>> viewFour(BaseDTO dto) {
        return ResponseData.data(iBbcMarketActivityRpc.viewFour(dto));
    }
    @ApiOperation("满折商品全部")
    @GetMapping("/marketActivity/discount")
    public ResponseData<PageData<BbcMarketActivityVO.discountVO>> discountList(BbcMarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.discountList(qto));
    }
    @ApiOperation("满折商品显示4个")
    @GetMapping("/marketActivity/discount/viewFour")
    public ResponseData<List<BbcMarketActivityVO.discountVO>> discountViewFour(BaseDTO dto) {
        return ResponseData.data(iBbcMarketActivityRpc.discountViewFour(dto));
    }

    @ApiOperation("满赠商品全部")
    @GetMapping("/marketActivity/giftList")
    public ResponseData<PageData<BbcMarketActivityVO.giftVO>> giftList(BbcMarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.giftList(qto));
    }
    @ApiOperation("满赠商品显示4个")
    @GetMapping("/marketActivity/gift/viewFour")
    public ResponseData<List<BbcMarketActivityVO.giftVO>> giftViewFour(BaseDTO dto) {
        return ResponseData.data(iBbcMarketActivityRpc.giftViewFour(dto));
    }

    @ApiOperation("团购商品全部")
    @GetMapping("/marketActivity/groupbuyList")
    public ResponseData<PageData<BbcMarketActivityVO.groupbuyVO>> groupbuyList(BbcMarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.groupbuyList(qto));
    }
    @ApiOperation("团购商品显示4个")
    @GetMapping("/marketActivity/groupbuy/viewFour")
    public ResponseData<List<BbcMarketActivityVO.groupbuyVO>> groupbuyViewFour(BaseDTO dto) {
        return ResponseData.data(iBbcMarketActivityRpc.groupbuyViewFour(dto));
    }

    @ApiOperation("活动")
    @GetMapping("/marketActivity/activity")
    public ResponseData<BbcMarketActivityVO.activityVO> activity(BbcMarketActivityQTO.IdQTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.activity(qto));
    }
    @ApiOperation("活动列表")
    @GetMapping("/marketActivity/activityList")
    public ResponseData<PageData<BbcMarketActivityVO.activityListPageVO>> activityListPage(BbcMarketActivityQTO.QTO qto) {
        return ResponseData.data(iBbcMarketActivityRpc.activityListPage(qto));
    }

    @ApiOperation("商家优惠卷(店铺页)")
    @PostMapping("/marketActivity/activityCard")
    public ResponseData<List<BbcMarketActivityVO.merchantCard>> merchantCard(@Valid @RequestBody BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {

        return ResponseData.data(iBbcMarketActivityRpc.merchantCard(dto));
    }

    @ApiOperation("商家优惠卷(商品详情页)")
    @PostMapping("/marketActivity/activityCardGoodsInfo")
    public ResponseData<List<BbcMarketActivityVO.merchantCard>> activityCardGoodsInfo(@Valid @RequestBody BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {

        return ResponseData.data(iBbcMarketActivityRpc.activityCardGoodsInfo(dto));
    }

    @ApiOperation("用户领取")
    @PostMapping("/userCenter/marketActivity/activityCard")
    public ResponseData<BbcMarketActivityVO.merchantCardSuccess> userReciveCard(@Valid @RequestBody BbcMarketMerchantActivityDTO.CardIdDTO dto) {

        return ResponseData.data(iBbcMarketActivityRpc.userReciveCard(dto));
    }
    @ApiOperation("活动权限控制")
    @PostMapping("/activity/jurisdiction")
    public ResponseData<BbcMarketActivityVO.jurisdiction> jurisdiction() {

        return ResponseData.data(iBbcMarketActivityRpc.jurisdiction());
    }



}
