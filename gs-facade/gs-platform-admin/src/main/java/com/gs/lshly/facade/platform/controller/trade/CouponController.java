package com.gs.lshly.facade.platform.controller.trade;

import java.util.Optional;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ICouponRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author chenyang
 */
@RestController
@RequestMapping("/platadmin/coupon")
@Api(tags = "优惠券管理")
@Module(code = "listCoupon",parent = "transaction",name = "优惠券",index = 1)
public class CouponController {

    @DubboReference
    private ICouponRpc iCouponRpc;

    @ApiOperation("新增优惠券")
    @PostMapping("/addCoupon")
    @Func(code = "add",name = "增")
    public ResponseData addCoupon(@RequestBody CouponQTO.SaveCouponQTO qto) {
        iCouponRpc.saveCoupon(qto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("更新优惠券")
    @PostMapping("/updateCoupon")
    @Func(code = "edit",name = "改")
    public ResponseData updateCoupon(@RequestBody CouponQTO.UpdateCouponQTO qto) {
        iCouponRpc.updateCoupon(qto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除优惠券")
    @GetMapping("/deleteCoupon")
    @Func(code = "delete",name = "删")
    public ResponseData deleteCoupon(@RequestParam("ids") String ids) {
        Optional.ofNullable(ids).orElseThrow(() ->new BusinessException("id不能为空"));
        iCouponRpc.deleteCoupon(ids);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("查看优惠券详情")
    @PutMapping("getCouponDetail/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<CouponVO.CouponDetailVO> getCouponDetail(@PathVariable String id) {
        Optional.ofNullable(id).orElseThrow(() ->new BusinessException("id不能为空"));
        CouponVO.CouponDetailVO detailVO = iCouponRpc.getDetail(id);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("优惠券列表展示")
    @GetMapping("/queryCouponList")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<CouponVO.CouponListVO>> queryCouponList(CouponQTO.CouponListQTO qto) {
        PageData<CouponVO.CouponListVO> pageData = iCouponRpc.queryCouponList(qto);
        return ResponseData.data(pageData);
    }

    @ApiOperation("修改优惠券库存数量")
    @PostMapping("/updateCouponStockNum")
    @Func(code = "edit",name = "改")
    public ResponseData updateCouponStockNum(@RequestBody CouponQTO.CouponStockQTO qto) {
        iCouponRpc.updateStockNum(qto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("停止发送")
    @GetMapping("/stopSendCoupon")
    @Func(code = "edit",name = "改")
    public ResponseData stopSendCoupon(@RequestParam("couponId") String couponId) {
        iCouponRpc.stopSend(couponId);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("审核优惠券")
    @PostMapping("/dealAduitCoupon")
    @Func(code = "edit",name = "改")
    public ResponseData dealAduitCoupon(@RequestBody CouponQTO.CouponAduitQTO qto) {
        iCouponRpc.dealAduitCoupon(qto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
}
