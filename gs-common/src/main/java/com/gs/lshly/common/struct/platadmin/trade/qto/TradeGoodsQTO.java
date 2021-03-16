package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeGoodsQTO implements Serializable {

    @Data
    @ApiModel("TradeGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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

        @ApiModelProperty("SKU格规值")
        private String skuSpecValue;

        @ApiModelProperty("SKU图片")
        private String skuImg;

        @ApiModelProperty("sku商品货号")
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

        @ApiModelProperty("交易总价占比")
        private BigDecimal tradeAmountPercent;

        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;
    }


    @Data
    @ApiModel("TradeGoodsDTO.GoodsSale")
    @Accessors(chain = true)
    public static class GoodsSale extends BaseQTO {

        @ApiModelProperty(value = "交易商品ID",hidden = true)
        private String id;

        @ApiModelProperty("开始时间")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        private LocalDateTime endTime;

        @ApiModelProperty("时间选择[10昨天 20前天 30一周 40一月]")
        private Integer queryTimes;

        @ApiModelProperty("查询选择[10销售数量 20销售金额]")
        private Integer queryStates;
    }
}
