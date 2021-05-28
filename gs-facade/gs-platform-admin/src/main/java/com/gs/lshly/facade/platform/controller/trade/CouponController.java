package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.rpc.api.platadmin.trade.ICouponRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 * @author chenyang
 */
@RestController
@RequestMapping("/platadmin/coupon")
@Api(tags = "优惠券管理")
public class CouponController {

    @DubboReference
    private ICouponRpc iCouponRpc;

    @ApiOperation("新增优惠券")
    @PostMapping("/addCoupon")
    public ResponseData addCoupon(@RequestBody CouponQTO.SaveCouponQTO qto) {
        iCouponRpc.saveCoupon(qto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("更新优惠券")
    @PostMapping("/updateCoupon")
    public ResponseData updateCoupon(@RequestBody CouponQTO.UpdateCouponQTO qto) {
        CouponDTO.UpdateCouponDTO dto = new CouponDTO.UpdateCouponDTO();
        BeanUtils.copyProperties(qto,dto);
        iCouponRpc.updateCoupon(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除优惠券")
    @GetMapping("/deleteCoupon")
    public ResponseData deleteCoupon(@RequestParam("id") Long id) {
        Optional.ofNullable(id).orElseThrow(() ->new BusinessException("id不能为空"));
        iCouponRpc.deleteCoupon(id);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("查看优惠券详情")
    @PutMapping("getCouponDetail/{id}")
    public ResponseData<CouponVO.CouponListVO> getCouponDetail(@PathVariable Long id) {
        Optional.ofNullable(id).orElseThrow(() ->new BusinessException("id不能为空"));
        CouponVO.CouponDetailVO detailVO = iCouponRpc.getDetail(id);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("优惠券列表展示")
    @GetMapping("/queryCouponList")
    public ResponseData<PageData<CouponVO.CouponListVO>> queryCouponList(CouponQTO.CouponListQTO qto) {
        PageData<CouponVO.CouponListVO> pageData = iCouponRpc.queryCouponList(qto);
        return ResponseData.data(pageData);
    }

    @ApiOperation("修改优惠券库存数量")
    @PostMapping("/updateCouponStockNum")
    public ResponseData updateCouponStockNum(@RequestBody CouponQTO.CouponStockQTO qto) {
        iCouponRpc.updateStockNum(qto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("停止发送")
    @GetMapping("/stopSendCoupon")
    public ResponseData stopSendCoupon(@RequestParam("couponId") Long couponId) {
        iCouponRpc.stopSend(couponId);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("审核优惠券")
    @PostMapping("/dealAduitCoupon")
    public ResponseData dealAduitCoupon(@RequestBody CouponQTO.CouponAduitQTO qto) {
        iCouponRpc.dealAduitCoupon(qto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
}
