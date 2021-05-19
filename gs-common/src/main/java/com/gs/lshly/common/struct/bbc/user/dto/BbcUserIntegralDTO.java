package com.gs.lshly.common.struct.bbc.user.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author zdf
* @since 2021-01-13
*/
@SuppressWarnings("serial")
public abstract class BbcUserIntegralDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserIntegralDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("订单金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("积分来源[10=平台添加 20=订单 30=签到]")
        private Integer fromType;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("业务ID")
        private String fromId;
    }
    @EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserIntegralDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }
    @EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcUserIntegralDTO.SubIntergralDTO")
    @AllArgsConstructor
    public static class SubIntergralDTO extends BaseDTO {
    	@ApiModelProperty("会员ID")
        private String userId;

    	@ApiModelProperty("扣减的积分值")
        private Integer Intergral;

    }

}
