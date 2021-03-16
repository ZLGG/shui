package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-09
*/
public abstract class TradeMarginDTO implements Serializable {

    @Data
    @ApiModel("TradeMarginDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型[10品牌旗舰店 20品牌专卖店 30类目专营店 40运营商自营 50多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("保证金额度")
        private BigDecimal marginQuota;

        @ApiModelProperty("保证金余额")
        private BigDecimal marginBalance;

        @ApiModelProperty("保证金账户状态 (10正常 20预警 30欠费)")
        private Integer marginType;
    }

    @Data
    @ApiModel("TradeMarginDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("TradeMarginDTO.QuotaDTO")
    @AllArgsConstructor
    public static class QuotaDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保证金额度")
        private BigDecimal marginQuota;
    }

    @Data
    @ApiModel("TradeMarginDTO.ChargeDTO")
    @AllArgsConstructor
    public static class ChargeDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保证金余额")
        private BigDecimal marginDeduction;

        @ApiModelProperty("银行交易流水号")
        private String bankSerialNum;

        @ApiModelProperty("备注")
        private String comment;
    }

    @Data
    @ApiModel("TradeMarginDTO.DeductionDTO")
    @AllArgsConstructor
    public static class DeductionDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保证金余额")
        private BigDecimal marginDeduction;

        @ApiModelProperty("关联订单编号")
        private String tradeCode;

        @ApiModelProperty("处罚理由")
        private String penaltyReason;

        @ApiModelProperty("违规描述")
        private String illegalDescription;

        @ApiModelProperty("备注")
        private String comment;
    }


    @Data
    @ApiModel("TradeMarginDTO.saveMargin")
    @AllArgsConstructor
    public static class saveMargin extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保证金额度")
        private BigDecimal marginQuota;
    }

    @Data
    @ApiModel("TradeMarginDTO.InnerCreateMargin")
    public static class InnerCreateMargin extends BaseDTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型[10品牌旗舰店 20品牌专卖店 30类目专营店 40运营商自营 50多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("保证金额度")
        private BigDecimal marginQuota;

        @ApiModelProperty("保证金余额")
        private BigDecimal marginBalance;

        @ApiModelProperty("保证金告警线")
        private BigDecimal marginDown;

        @ApiModelProperty("保证金账户状态 (10正常 20预警 30欠费)")
        private Integer marginType;

    }

}
