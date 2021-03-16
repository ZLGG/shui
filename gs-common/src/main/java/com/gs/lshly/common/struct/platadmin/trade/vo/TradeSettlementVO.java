package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author zst
* @since 2020-11-30
*/
public class TradeSettlementVO implements Serializable {

    @Data
    @ApiModel("TradeSettlementVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("结算汇总ID")
        private String id;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("所属商家")
        private String shopName;


        @ApiModelProperty("账单编号")
        private String tradeCode;


        @ApiModelProperty("订单数量")
        private Integer quantity;


        @ApiModelProperty("销售价")
        private BigDecimal salePrice;


        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;


        @ApiModelProperty("退款金额")
        private BigDecimal tradeAmount;


        @ApiModelProperty("佣金")
        private BigDecimal commission;


        @ApiModelProperty("结算金额")
        private BigDecimal settlementAmount;


        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;


        @ApiModelProperty("账单结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;


        @ApiModelProperty("结算时间")
        private LocalDateTime settlementTime;


        @ApiModelProperty("结算状态[10 未结算 20已结算]")
        private Integer settlementState;


        @ApiModelProperty("付款方式[10线上付款 20线下付款]")
        private Integer payType;




    }

    @Data
    @ApiModel("TradeSettlementVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable {
    }


    @Data
    @ApiModel("TradeSettlementVO.SaleslVO")
    @Accessors(chain = true)
    public static class SaleslVO implements Serializable {

        @ApiModelProperty("销售金额")
        private BigDecimal settlementAmount;

        @ApiModelProperty("订单数量")
        private Integer orderQuantity;

        @ApiModelProperty("商品数量")
        private Integer shopQuantity;

        @ApiModelProperty("平均订单价")
        private BigDecimal aveGprice;

        @ApiModelProperty(value = "订单数量组")
        private Map<String,Object> orderQuantityGroup;

        @ApiModelProperty(value = "销售金额/数量集合")
        private List<PackDateList> packDateList;

        @ApiModelProperty(value = "数量集合")
        private List<PackCountList> packCountList;
    }

    @Data
    @ApiModel("TradeSettlementVO.PackDateList")
    @Accessors(chain = true)
    public static class PackDateList implements Serializable {

        @ApiModelProperty("时间")
        private LocalDateTime cdate;

        @ApiModelProperty("金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("数量")
        private Integer count;
    }

    @Data
    @ApiModel("TradeSettlementVO.PackCountList")
    @Accessors(chain = true)
    public static class PackCountList implements Serializable {

        @ApiModelProperty("数量")
        private Integer count;
    }

}
