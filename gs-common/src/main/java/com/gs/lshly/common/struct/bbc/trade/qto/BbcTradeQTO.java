package com.gs.lshly.common.struct.bbc.trade.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
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
* @since 2020-10-28
*/
public abstract class BbcTradeQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeQTO.QTO")
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

        @ApiModelProperty("收货时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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

        @ApiModelProperty("是否隐藏订单:1:是")
        private Integer isHide;
    }

    @Data
    @ApiModel("BbcTradeQTO.TradeList")
    @Accessors(chain = true)
    public static class TradeList extends BaseQTO {


        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("关键字")
        private String keyword;

    }
    @Data
    @ApiModel("BbcTradeQTO.UserCardQTO")
    @Accessors(chain = true)
    public static class UserCardQTO extends BaseQTO {
        @ApiModelProperty("状态[10=未使用 20=已使用 30=已过期]")
        private Integer state;

        @ApiModelProperty("过期时间[10=升序 20=降序]")
        private Integer expirationTime;

        @ApiModelProperty("优惠金额[10=升序 20=降序]")
        private Integer preferentialAmount;

    }

}
