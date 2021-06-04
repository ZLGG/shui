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
* @author oy
* @since 2020-11-16
*/
public abstract class TradeDTO implements Serializable {

    @Data
    @ApiModel("TradeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易订单表(ID)",hidden = true)
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;

        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;

        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("创建时间")
        private LocalDateTime createTime;

        @ApiModelProperty("支付时间")
        private LocalDateTime payTime;

        @ApiModelProperty("收货时间")
        private LocalDateTime recvTime;

        @ApiModelProperty("支付类型")
        private Integer payType;

        @ApiModelProperty("配送类型")
        private Integer deliveryType;

        @ApiModelProperty("自提码")
        private String takeGoodsCode;

        @ApiModelProperty("自提码图片")
        private String takeGoodsQrcode;

        @ApiModelProperty("收货地址ID")
        private String recvAddresId;

        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;

        @ApiModelProperty("是否超时取消")
        private Integer timeoutCancel;

        @ApiModelProperty("买家留言")
        private String buyerRemark;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

        @ApiModelProperty("隐藏订单:1:是")
        private Boolean isHide;
    }

    @Data
    @ApiModel("TradeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易订单表(ID)")
        private String id;
    }

    @Data
    @ApiModel("TradeDTO.PlatformCancelDTO")
    @AllArgsConstructor
    public static class PlatformCancelDTO extends BaseDTO {

        @ApiModelProperty(value = "交易订单表(ID)")
        private String id;

        @ApiModelProperty(value = "运营平台处理说明")
        private String cancelReason;
    }

    @Data
    @ApiModel("TradeDTO.PayDateList")
    @Accessors(chain = true)
    public static class PayDateList extends BaseDTO {

        @ApiModelProperty(value = "交易商品ID",hidden = true)
        private String id;

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime endTime;

        @ApiModelProperty("时间选择[10昨天 20前天 30一周 40一月]")
        private Integer queryTimes;

        @ApiModelProperty("查询选择[10新增订单金额 20新增订单数量 30平均单价 40已取消金额 50已取消数量]")
        private Integer queryStates;
    }

    @Data
    @ApiModel("TradeDTO.OperationList")
    @Accessors(chain = true)
    public static class OperationList extends BaseDTO {

        @ApiModelProperty(value = "交易商品ID",hidden = true)
        private String id;

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime endTime;

        @ApiModelProperty("时间选择[10昨天 20前天 30一周 40一月]")
        private Integer queryTimes;

        @ApiModelProperty("查询选择[10新增订单金额 20新增订单数量 30平均单价 40付款订单金额 50已完成订单数 60已取消数量]")
        private Integer queryStates;
    }


    @Data
    @ApiModel("TradeDTO.PerformanceListDTO")
    @Accessors(chain = true)
    public static class PerformanceListDTO extends BaseDTO {

        @ApiModelProperty("年")
        private String year;

        @ApiModelProperty("店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("TradeDTO.ClientListDTO")
    @Accessors(chain = true)
    public static class ClientListDTO extends BaseDTO {

        @ApiModelProperty("年")
        private String year;

        @ApiModelProperty("客户姓名")
        private String userName;

        @ApiModelProperty("店铺id")
        private String shopId;

    }
}
