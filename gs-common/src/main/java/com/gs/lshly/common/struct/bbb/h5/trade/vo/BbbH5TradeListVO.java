package com.gs.lshly.common.struct.bbb.h5.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class BbbH5TradeListVO implements Serializable {


    @Data
    @ApiModel("BbbH5TradeListVO.tradeVO")
    @Accessors(chain = true)
    public static class tradeVO implements Serializable{

        @ApiModelProperty("交易订单号(ID)")
        private String id;


        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员名称")
        private String userName;


        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("交易编号")
        private String tradeCode;


        @ApiModelProperty("交易状态")
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
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty("支付截止时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;


        @ApiModelProperty("支付类型")
        private Integer payType;


        @ApiModelProperty("配送类型")
        private Integer deliveryType;

        @ApiModelProperty("物流单号")
        private String logisticsNumber;

        @ApiModelProperty("物流公司代码")
        private String logisticsCompanyCode;

        @ApiModelProperty("物流公司名称")
        private String logisticsCompanyName;

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

        @ApiModelProperty("交易商品集合")
        List<TradeGoodsVO> tradeGoodsVOS;

    }

    @Data
    @ApiModel("BbbH5TradeListVO.TradeGoodsVO")
    @Accessors(chain = true)
    public static class TradeGoodsVO implements Serializable{

        @ApiModelProperty("交易商品ID")
        private String id;


        @ApiModelProperty("交易ID")
        private String tradeId;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("商品ID")
        private String goodsId;


        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;


        @ApiModelProperty("SKU ID")
        private String skuId;


        @ApiModelProperty("格规值")
        private String skuSpecValue = "";

        @ApiModelProperty("SKU IMG")
        private String skuImg;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;


        @ApiModelProperty("数量")
        private Integer quantity;


        @ApiModelProperty("销售价")
        private BigDecimal salePrice;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("支付总金额")
        private BigDecimal payAmount;


        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;


        @ApiModelProperty("订单总价占比")
        private BigDecimal tradeAmountPercent;

        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;

    }
    @Data
    @ApiModel("BbbH5TradeListVO.stateCount")
    public static class stateCountVO implements Serializable{

        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("交易数量")
        private Integer tradeCount;

    }

    @Data
    @ApiModel("BbbH5TradeListVO.UseCard")
    @Accessors(chain = true)
    public static class UseCard implements Serializable{

        @ApiModelProperty("优惠卷id")
        private String cardId;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        @ApiModelProperty("优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String cardDescribe;

        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("是否隐藏[10=隐藏 20=不隐藏]")
        private Integer isHide;

    }
    @Data
    @ApiModel("BbbH5TradeListVO.InnerGoodsCompareTo")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class InnerGoodsCompareTo implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;

        @ApiModelProperty("序号")
        private Integer idx;
    }
    @Data
    @ApiModel("BbbH5TradeListVO.InnerCompareTo")
    @AllArgsConstructor
    public static class InnerCompareTo implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;


        @ApiModelProperty("评分")
        private Double grade;
    }
    @Data
    @ApiModel("BbbH5TradeListVO.InnerCompareToCount")
    public static class InnerCompareToCount implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;


        @ApiModelProperty("销量")
        private Integer count;
    }


}
