package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeQTO implements Serializable {

    @Data
    @ApiModel("TradeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
    @ApiModel("TradeQTO.IdListQTO")
    public static class IdListQTO implements Serializable{

        @ApiModelProperty("订单ID列表")
        private List<String> idList;

    }

    @Data
    @ApiModel("TradeQTO.IdDateQTO")
    public static class IdDateQTO extends BaseQTO{

        @ApiModelProperty("订单ID列表")
        private List<String> idList;

    }

    @Data
    @ApiModel("TradeQTO.TradeList")
    @Accessors(chain = true)
    public static class TradeList extends BaseQTO {

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime createTime;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;

        @ApiModelProperty("会员账号")
        private String userName;

        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("配送类型")
        private Integer deliveryType;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;
    }


    @Data
    @ApiModel("TradeQTO.OperationList")
    @Accessors(chain = true)
    public static class OperationList extends BaseQTO {

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
    @ApiModel("TradeQTO.PayDateList")
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
}
