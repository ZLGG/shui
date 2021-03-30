package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/18 10:31
 */
@RestController
@RequestMapping("/bbc/pc/userCenter/inUserCoupon")
@Api(tags = "in会员优惠券管理-v1.1.0")
public class BbcPcInUserCouponController {

    @DubboReference
    private IBBBInUserCouponRpc inUserCouponRpc;

    @ApiOperation("in会员优惠券列表")
    @PostMapping("/list")
    public ResponseData<List<BbcInUserCouponVO>> queryInUserCouponList(@Valid @RequestBody BbbInUserCouponQTO .QTO qto) {
        if (StringUtils.isEmpty(qto.getUserId())) {
            throw new BusinessException("用户未登陆");
        }
        return ResponseData.data(inUserCouponRpc.queryInUserCouponList(qto));
    }
}
