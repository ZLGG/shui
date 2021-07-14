package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class PCMerchTradeVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易订单表(ID)")
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
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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
    @ApiModel("PCMerchTradeVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("PCMerchTradeVO.ExcelReturnVO")
    public static class ExcelReturnVO implements Serializable {

        @ApiModelProperty("总数")
        private Integer totalNum;

        @ApiModelProperty("成功数")
        private Integer successNum;

        @ApiModelProperty("失败数")
        private Integer errorNum;

        @ApiModelProperty("错误信息")
        private List<ErrorMsgVO> errorMsgVOS;

    }

    @Data
    @ApiModel("PCMerchTradeVO.ErrorMsgVO")
    public static class ErrorMsgVO implements Serializable {

        @ApiModelProperty("错误信息")
        private String msg;
    }

    @Data
    @ApiModel("PCMerchTradeVO.DownExcelModelVO")
    public static class DownExcelModelVO implements Serializable {

        @ApiModelProperty(value = "订单编号",position = 1)
        private String tradeCode;

        @ApiModelProperty(value = "物流公司",position = 2)
        private String logisticsCompanyName;

        @ApiModelProperty(value = "快递单号",position = 3)
        private String logisticsNumber;

        @ApiModelProperty(value = "发货备注",position = 4)
        private String deliveryRemark;
    }
}
