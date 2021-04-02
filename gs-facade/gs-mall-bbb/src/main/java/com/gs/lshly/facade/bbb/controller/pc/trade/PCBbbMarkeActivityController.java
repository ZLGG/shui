package com.gs.lshly.facade.bbb.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketActivityRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
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
@RequestMapping("/bbb")
@Api(tags = "活动展示")
public class PCBbbMarkeActivityController {

    @DubboReference
    private IPCBbbMarketActivityRpc ipcBbbMarketActivityRpc;

    @ApiOperation("满减商品全部")
    @GetMapping("/marketActivity")
    public ResponseData<PageData<PCBbbMarketActivityVO.cutVO>> cutList(PCBbbMarketActivityQTO.QTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.cutList(qto));
    }
    @ApiOperation("满减商品显示4个")
    @GetMapping("/marketActivity/viewFour")
    public ResponseData<List<PCBbbMarketActivityVO.cutVO>> viewFour(BaseDTO dto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.viewFour(dto));
    }
    @ApiOperation("满折商品全部")
    @GetMapping("/marketActivity/discount")
    public ResponseData<PageData<PCBbbMarketActivityVO.discountVO>> discountList(PCBbbMarketActivityQTO.QTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.discountList(qto));
    }
    @ApiOperation("满折商品显示4个")
    @GetMapping("/marketActivity/discount/viewFour")
    public ResponseData<List<PCBbbMarketActivityVO.discountVO>> discountViewFour(BaseDTO dto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.discountViewFour(dto));
    }

    @ApiOperation("满赠商品全部")
    @GetMapping("/marketActivity/giftList")
    public ResponseData<PageData<PCBbbMarketActivityVO.giftVO>> giftList(PCBbbMarketActivityQTO.QTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.giftList(qto));
    }
    @ApiOperation("满赠商品显示4个")
    @GetMapping("/marketActivity/gift/viewFour")
    public ResponseData<List<PCBbbMarketActivityVO.giftVO>> giftViewFour(BaseDTO dto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.giftViewFour(dto));
    }

    @ApiOperation("团购商品全部")
    @GetMapping("/marketActivity/groupbuyList")
    public ResponseData<PageData<PCBbbMarketActivityVO.groupbuyVO>> groupbuyList(PCBbbMarketActivityQTO.QTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.groupbuyList(qto));
    }
    @ApiOperation("团购商品显示4个")
    @GetMapping("/marketActivity/groupbuy/viewFour")
    public ResponseData<List<PCBbbMarketActivityVO.groupbuyVO>> groupbuyViewFour(BaseDTO dto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.groupbuyViewFour(dto));
    }

    @ApiOperation("活动")
    @GetMapping("/marketActivity/activity")
    public ResponseData<PCBbbMarketActivityVO.activityVO> activity(PCBbbMarketActivityQTO.IdQTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.activity(qto));
    }

    @ApiOperation("活动列表")
    @GetMapping("/marketActivity/activityList")
    public ResponseData<PageData<PCBbbMarketActivityVO.activityListPageVO>> activityListPage(PCBbbMarketActivityQTO.QTO qto) {
        return ResponseData.data(ipcBbbMarketActivityRpc.activityListPage(qto));
    }

    @ApiOperation("活动(商品详情)")
    @PostMapping("/marketActivity/activityDetail")
    public ResponseData<PCBbbMarketActivityVO.ListActivityVO> activityList(@Valid @RequestBody BbbMarketMerchantActivityDTO.IdDTO dto) {

        return ipcBbbMarketActivityRpc.activityList(dto);
    }

    @ApiOperation("商家优惠卷(店铺界面)")
    @PostMapping("/marketActivity/activityCard")
    public ResponseData<List<PCBbbMarketActivityVO.merchantCard>> merchantCard(@Valid @RequestBody BbbMarketMerchantActivityDTO.MerchantIdDTO dto) {

        return ResponseData.data(ipcBbbMarketActivityRpc.merchantCard(dto));
    }
    @ApiOperation("商家优惠卷(商品详情页)")
    @PostMapping("/marketActivity/activityCardGoodsInfo")
    public ResponseData<List<PCBbbMarketActivityVO.merchantCard>> activityCardGoodsInfo(@Valid @RequestBody BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {

        return ResponseData.data(ipcBbbMarketActivityRpc.activityCardGoodsInfo(dto));
    }

    @ApiOperation("用户领取")
    @PostMapping("/userCenter/marketActivity/activityCard")
    public ResponseData<PCBbbMarketActivityVO.merchantCardSuccess> userReciveCard(@Valid @RequestBody BbbMarketMerchantActivityDTO.CardIdDTO dto) {

        return ResponseData.data(ipcBbbMarketActivityRpc.userReciveCard(dto));
    }

    @ApiOperation("活动权限控制")
    @PostMapping("/activity/jurisdiction")
    public ResponseData<PCBbbMarketActivityVO.jurisdiction> jurisdiction() {

        return ResponseData.data(ipcBbbMarketActivityRpc.jurisdiction());
    }




}
