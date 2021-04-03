package com.gs.lshly.common.struct.bbb.pc.user.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/3/18 10:04
 */
@Data
public class PCBBBInUserCouponDTO implements Serializable {
    @Data
    @ApiModel("PCBBBInUserCouponDTO.DTO")
    @Accessors(chain = true)
    public static class DTO implements Serializable {
        @ApiModelProperty("优惠券id")
        private Long couponId;

        @ApiModelProperty("in会员userId")
        private String userId;

        @ApiModelProperty("优惠券使用开始时间")
        private LocalDate startTime;

        @ApiModelProperty("优惠券使用结束时间")
        private LocalDate endTime;

        @ApiModelProperty("优惠券抵扣金额")
        private BigDecimal couponPrice;

        @ApiModelProperty("优惠券类型（0-成为会员赠送 1-会员每月分享赠送")
        private Integer couponType;

        @ApiModelProperty("优惠券状态（0-未使用 1-已使用 2-已过期）")
        private Integer couponStatus;

        @ApiModelProperty("优惠券说明")
        private String couponDesc;

        @ApiModelProperty("创建时间")
        private Date createTime;

        @ApiModelProperty("更新时间")
        private Date modifyTime;
    }
}

