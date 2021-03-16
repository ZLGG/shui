package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
* @since 2020-12-01
*/
public abstract class TradeSettlementDetailDTO implements Serializable {

    @Data
    @ApiModel("TradeSettlementDetailDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "结算明细ID",hidden = true)
        private String id;

        @ApiModelProperty("订单编号")
        private String tradeCode;

        @ApiModelProperty("结算汇总ID")
        private String settlementId;

        @ApiModelProperty("商家ID")
        private String shopId;

        @ApiModelProperty("所属商家")
        private String shopName;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("明细商品的编码(商品货号)")
        private String goodsNo;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品价格")
        private BigDecimal salePrice;

        @ApiModelProperty("购买数量")
        private Integer quantity;

        @ApiModelProperty("优惠分摊")
        private BigDecimal apportionDiscounts;

        @ApiModelProperty("商品款(不包含运费)")
        private BigDecimal commodityMoney;

        @ApiModelProperty("运费")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("退款方式")
        private Integer refundWay;

        @ApiModelProperty("退款金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("佣金")
        private BigDecimal commission;

        @ApiModelProperty("结算金额")
        private BigDecimal settlementAmount;

        @ApiModelProperty("结算类型")
        private Integer settlementType;

        @ApiModelProperty("子订单级订单优惠金额	")
        private BigDecimal apportionSon;

        @ApiModelProperty("可结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime settleTime;

        @ApiModelProperty("付款时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime payTime;

        @ApiModelProperty("付款方式")
        private Integer payType;
    }

    @Data
    @ApiModel("TradeSettlementDetailDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "结算明细ID")
        private String id;
    }


}
