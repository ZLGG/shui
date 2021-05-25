package com.gs.lshly.common.struct.bbc.trade.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author oy
* @since 2020-11-04
*/
@SuppressWarnings("serial")
public abstract class BbcTradePayBuildDTO implements Serializable {

	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcTradePayBuildDTO.CheckAndPointDoPayETO")
    @Accessors(chain = true)
    public static class CheckAndPointDoPayETO extends BaseDTO {

        @ApiModelProperty("交易ID")
        private List<String> tradeIds;

        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "验证码")
        private String validCode;

    }
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradePayBuildDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("交易ID")
        private List<String> tradeIds;

        @ApiModelProperty("微信openid")
        private String openid;

        @ApiModelProperty(value = "线下支付记录表ID",hidden = true)
        private String id;

        @ApiModelProperty("支付ID")
        private String payId;

        @ApiModelProperty("支付凭证")
        private String payOrder;

        @ApiModelProperty("转帐金额")
        private BigDecimal payAmount;

        @ApiModelProperty("账户名")
        private String accountName;

        @ApiModelProperty("银行账号")
        private String accountNumber;

        @ApiModelProperty("银行名称")
        private String bankName;

        @ApiModelProperty("转帐备注")
        private String payRemark;

    }
	
}
