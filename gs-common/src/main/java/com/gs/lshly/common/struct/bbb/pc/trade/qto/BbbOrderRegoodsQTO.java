package com.gs.lshly.common.struct.bbb.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zhaoyan
* @since 2020-10-15
*/
public abstract class BbbOrderRegoodsQTO implements Serializable {

    @Data
    @ApiModel("BBB.OrderRegoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("订单商品项ID")
        private String orderItemId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("SKU_ID")
        private String skuId;

        @ApiModelProperty("格规ID")
        private String specifiId;

        @ApiModelProperty("销售价")
        private Float salePrice;

        @ApiModelProperty("支付价")
        private Float payPrice;

        @ApiModelProperty("补贴(商家补贴)(平台补贴)")
        private Float benefits;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("支付总金额")
        private Float payPriceCount;

        @ApiModelProperty("所得积分")
        private Integer giftIntegral;

        @ApiModelProperty("订单总价占比")
        private Float orderPricePercent;

        @ApiModelProperty("退货状态[10=申请已提交等待商家处理 20=商家已接受等待回寄 30=已回寄等待商家确认 40=商家已收货等待回款 50=完成]")
        private Integer state;

        @ApiModelProperty("退货原因")
        private String why;

        @ApiModelProperty("拒绝退货原因")
        private String rejectWhy;

        @ApiModelProperty("审核人名字")
        private String pHandName;

        @ApiModelProperty("审核人帐号ID")
        private String pHandId;

        @ApiModelProperty("发货时间")
        private LocalDateTime sendTime;

        @ApiModelProperty("发货地址")
        private String sendAddress;

        @ApiModelProperty("联系人电话")
        private String contactPhone;

        @ApiModelProperty("订单ID")
        private String orderId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }
}
