package com.gs.lshly.common.struct.bbc.user.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月15日 下午6:01:04
 */
@SuppressWarnings("serial")
public class BbcUserCouponVO implements Serializable {

    @ApiModel("BbcUserCouponVO.ListVO")
    @Data
    public static class ListVO implements Serializable {
    	
        private String id;

        private String couponId;

        private String userId;

        private LocalDate startTime;

        private LocalDate endTime;

        private BigDecimal couponPrice;

        private BigDecimal minPrice;

        private Integer couponType;

        private Integer couponStatus;

        private String couponName;

        private String couponDesc;

        private LocalDate createTime;

        private LocalDate modifyTime;

    }

}

