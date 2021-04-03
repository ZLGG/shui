package com.gs.lshly.common.struct.bbc.user.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/3/18 10:04
 */
@Data
@TableName(value = "gs_in_vip_coupon")
public class BbcInUserCouponVO implements Serializable {
    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("in会员userId")
    private String userId;

    @ApiModelProperty("优惠券使用开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("优惠券使用结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("优惠券抵扣金额")
    private BigDecimal couponPrice;

    @ApiModelProperty("最低消费多少金额可用优惠券")
    private BigDecimal minPrice;

    @ApiModelProperty("优惠券说明")
    private String couponDesc;
}

