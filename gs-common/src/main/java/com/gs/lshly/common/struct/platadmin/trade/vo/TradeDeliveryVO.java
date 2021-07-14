package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeDeliveryVO implements Serializable {

    @Data
    @ApiModel("TradeDeliveryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("发货表(ID)")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("物流公司名字")
        private String logisticsName;

        @ApiModelProperty("操作员id")
        private String operatorId;

        @ApiModelProperty("操作员姓名")
        private String operatorName;

        @ApiModelProperty("发货时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime deliveryTime;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

        @ApiModelProperty("单据创建时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("单据确认时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime confirmTime;

        @ApiModelProperty("单据结束时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @ApiModelProperty("物流费用")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("业务号码")
        private String businessPhone;

        @ApiModelProperty("客户编号")
        private String customerID;



    }
    @Data
    @ApiModel("发货单")
    @Accessors(chain = true)
    public static class ListExportVO implements Serializable {

        @ApiModelProperty(value = "发货表(ID)",position = 1)
        private String id;

        @ApiModelProperty(value = "交易编号",position = 2)
        private String tradeCode;

        @ApiModelProperty(value = "订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消",position = 3)
        private String tradeState;

        @ApiModelProperty(value = "会员名字",position = 4)
        private String userName;

        @ApiModelProperty(value = "店铺名字",position = 5)
        private String shopName;

        @ApiModelProperty(value = "物流编号",position = 6)
        private String logisticsNumber;

        @ApiModelProperty(value = "物流公司名字",position = 7)
        private String logisticsName;

        @ApiModelProperty(value = "操作员姓名",position = 8)
        private String operatorName;

        @ApiModelProperty(value = "发货时间",position = 9)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime deliveryTime;
        @ApiModelProperty(value = "发货备注",position = 10)
        private String deliveryRemark;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("TradeDeliveryVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("发货单创建时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
        @ApiModelProperty("运费")
        private BigDecimal deliveryAmount;
        @ApiModelProperty("收货名字")
        private String recvPersonName;
        @ApiModelProperty("收货地址")
        private String recvFullAddres;
        @ApiModelProperty("收货人电话")
        private String recvPhone;
        @ApiModelProperty("配送类型")
        private Integer deliveryType;
        @ApiModelProperty("商品信息")
        private List<TradeGoodsVO> goodsVOS;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("TradeDeliveryVO.TradeGoodsVO")
    public static class TradeGoodsVO implements Serializable {
        @ApiModelProperty("商品名称")
        private String goodsName;
        @ApiModelProperty("货号")
        private String goodsNo;
        @ApiModelProperty("单价")
        private BigDecimal salePrice;
        @ApiModelProperty("商品总额")
        private BigDecimal payAmount;
    }
}
