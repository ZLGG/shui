package com.gs.lshly.common.struct.bbc.user.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月15日 下午6:01:04
 */
@SuppressWarnings("serial")
public class BbcUserCouponQTO implements Serializable {

    @ApiModel("BbcUserCouponQTO.ListByCouponIdQTO")
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class ListByCouponIdQTO extends BaseQTO {
        private String couponId;
    }

}

