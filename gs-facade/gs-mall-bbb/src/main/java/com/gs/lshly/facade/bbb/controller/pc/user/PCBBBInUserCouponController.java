package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/18 10:31
 */
@RestController
@RequestMapping("/bbb/userCenter/inUserCoupon")
@Api(tags = "in会员优惠券管理-v1.1.0")
public class PCBBBInUserCouponController {

    @DubboReference
    private IBBBInUserCouponRpc inUserCouponRpc;

    @ApiOperation("in会员优惠券列表")
    @PostMapping("/list")
    public ResponseData<List<PCBBBInUserCouponVO>> queryInUserCouponList(@Valid @RequestBody BbbInUserCouponQTO .QTO qto) {
        if (StringUtils.isEmpty(qto.getUserId())) {
            throw new BusinessException("用户未登陆");
        }
        return ResponseData.data(inUserCouponRpc.queryInUserCouponList(qto));
    }
}
