package com.gs.lshly.facade.bbc.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcInUserCouponRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/18 10:31
 */
@RestController
@RequestMapping("/bbc/userCenter/userCoupon")
@Api(tags = "会员卡券中心管理-v1.1.0")
public class BbcInUserCouponController {

    @DubboReference
    private IBbcInUserCouponRpc inUserCouponRpc;

    @ApiOperation("会员优惠券列表")
    @GetMapping("/list")
    public ResponseData<List<BbcInUserCouponVO.ListVO>> queryInUserCouponList(BbcInUserCouponQTO.QTO qto) {
        return ResponseData.data(inUserCouponRpc.queryInUserCouponList(qto));
    }

    @ApiOperation("in会员会员卡列表")
    @GetMapping("/getCardList")
    public ResponseData<List<BbcInUserCouponVO.CardList>> getCardList(BbcInUserCouponQTO.QTO qto) {
        List<BbcInUserCouponVO.CardList> resultList = inUserCouponRpc.getCardList(qto);
        return ResponseData.data(resultList);
    }

    @PostMapping("/couponByBuy")
    @ApiOperation("通过购买in会员获得优惠券")
    public ResponseData getCouponByBuy(@Valid @RequestBody BbcInUserCouponQTO.BuyCouponQTO qto) {
        inUserCouponRpc.getCouponByBuy(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @PostMapping("/couponByShare")
    @ApiOperation("通过分享小程序获得优惠券")
    public ResponseData getCouponByShare(@Valid @RequestBody BbcInUserCouponQTO.ShareCouponQTO qto) {
        inUserCouponRpc.getCouponByShare(qto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("查看个人可用优惠券")
    @GetMapping("/getMyCouponToUse")
    public ResponseData<List<BbcInUserCouponVO.MyCouponListVO>> getMyCouponToUse(@Valid @RequestBody BbcInUserCouponQTO.MyCouponQTO qto) {
        List<BbcInUserCouponVO.MyCouponListVO> couponVOList = inUserCouponRpc.getMyCouponToUse(qto);
        return ResponseData.data(couponVOList);
    }
}
