package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-01
*/
public abstract class TradeSettlementDetailVO implements Serializable {

    @Data
    @ApiModel("TradeSettlementDetailVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;


        @ApiModelProperty(value = "订单编号",position = 2)
        private String tradeCode;


        @ApiModelProperty(value = "结算汇总ID",position = 3)
        private String settlementId;


        @ApiModelProperty(value = "商家ID",position = 4)
        private String shopId;


        @ApiModelProperty(value = "所属商家",position = 5)
        private String shopName;


        @ApiModelProperty(value = "商品ID",position = 6)
        private String goodsId;


        @ApiModelProperty(value = "商品名称",position = 7)
        private String goodsName;


        @ApiModelProperty(value = "明细商品的编码(商品货号)",position = 8)
        private String goodsNo;


        @ApiModelProperty(value = "商品标题",position = 9)
        private String goodsTitle;


        @ApiModelProperty(value = "商品价格",position = 10)
        private BigDecimal salePrice;


        @ApiModelProperty(value = "购买数量",position = 11)
        private Integer quantity;


        @ApiModelProperty(value = "优惠分摊",position = 12)
        private BigDecimal apportionDiscounts;


        @ApiModelProperty(value = "商品款(不包含运费)",position = 13)
        private BigDecimal commodityMoney;


        @ApiModelProperty(value = "运费",position = 14)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "退款方式",position = 15)
        private Integer refundWay;


        @ApiModelProperty(value = "退款金额",position = 16)
        private BigDecimal tradeAmount;


        @ApiModelProperty(value = "佣金",position = 17)
        private BigDecimal commission;


        @ApiModelProperty(value = "结算金额",position = 18)
        private BigDecimal settlementAmount;


        @ApiModelProperty(value = "结算类型",position = 19)
        private Integer settlementType;


        @ApiModelProperty(value = "子订单级订单优惠金额",position = 20)
        private BigDecimal apportionSon;


        @ApiModelProperty(value = "可结算时间",position = 21)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime settleTime;


        @ApiModelProperty(value = "付款时间",position = 22)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime payTime;


        @ApiModelProperty(value = "付款方式",position = 23)
        private Integer payType;


    }

    @Data
    @ApiModel("TradeSettlementDetailVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
