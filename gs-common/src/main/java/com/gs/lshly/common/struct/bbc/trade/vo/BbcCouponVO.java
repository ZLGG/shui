package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 优惠券
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:48:45
 */
@SuppressWarnings("serial")
public abstract class BbcCouponVO implements Serializable {
	
	
	/**
	 * 返回的消息
	 *
	 * 
	 * @author yingjun
	 * @date 2021年5月28日 下午3:23:38
	 */
	@Data
	@ApiModel("BbcCouponVO.InCouponVO")
	@Accessors(chain = true)
	public static class InCouponVO implements Serializable{
		
		@ApiModelProperty("优惠券ID")
        private String couponId;
    
    	@ApiModelProperty("优惠券Code")
        private String couponCode;
    	
    	@ApiModelProperty("名称")
        private String couponName;
    	
    	@ApiModelProperty("描述")
        private String couponDesc;
    	
    	@ApiModelProperty("优惠券抵扣金额")
        private BigDecimal couponPrice;
    	
    	@ApiModelProperty("使用门槛")
        private BigDecimal minPrice;
    	
    	@ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
    	private Integer couponType;
    	
    	@ApiModelProperty("优惠券状态（0-未使用 1-已使用 2-已过期）")
    	private Integer couponStatus;
    	
    	@ApiModelProperty("开始时间")
    	private LocalDate startTime;
    	@ApiModelProperty("结束时间")
    	private LocalDate endTime;
    	
    	
        
	}
}
