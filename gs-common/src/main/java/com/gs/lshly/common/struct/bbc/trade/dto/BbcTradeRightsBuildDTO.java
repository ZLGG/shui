package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author oy
 * @since 2020-12-06
 */
public abstract class BbcTradeRightsBuildDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("申请售后(退款)原因(10:不想要或多拍了,15:商品信息拍错(规格/尺码/颜色等),20:地址/电话信息填写错误," +
                "25:没用或少用优惠券,30:协商退款一致,35:缺货," +
                "40:其他,45:与商品详情不符,50:生产日期/保质期与商品不符,55:图片/产地/规格等描述不符," +
                "60:商品变质,65:商品破损/缺少配件,70:卖家发错货,75:不喜欢/不想要,80:空包裹," +
                "85:快递一直未送达,90:快递无跟踪记录,95:货物破损已拒签,100:取消订单,105:充值未到账,110充值少到账)")
        private Integer rightsReasonType;

        @ApiModelProperty("申请售后(退款,换货)说明")
        private String rightsRemark;

/*        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;*/

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("退款积分")
        private BigDecimal refundPoint;

        @ApiModelProperty(value = "订单商品信息")
        private List<ProductData> productData;

        @ApiModelProperty(value = "凭证图片集合")
        private List<RightsImgData> rightsImgData;

        @ApiModelProperty("买家收货地址Id")
        private String recvAddresId;

        @ApiModelProperty("买家收货人姓名")
        private String recvPersonName;

        @ApiModelProperty("买家电话")
        private String recvPhone;

        @ApiModelProperty(value = "买家收货地址全文本")
        private String recvFullAddres;

        @Data
        public static class ProductData implements Serializable {

            @ApiModelProperty(value = "订单商品sku ID")
            private String skuId;

            @ApiModelProperty(value = "退货数量")
            private Integer quantity;

            @ApiModelProperty(value = "原商品或换货商品(10:原商品,20:换货商品)")
            private Integer goodsType;
        }

        @Data
        public static class RightsImgData extends BaseDTO implements Serializable {

            @ApiModelProperty("凭证图片")
            private String rightsImg;

        }

    }

    @Data
    public static class RightsReturnGoodsLogistics extends BaseDTO implements Serializable {

        @ApiModelProperty("售后单ID")
        private String rightsId;

        @ApiModelProperty("退货物流公司名称")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;

        @ApiModelProperty("退货寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime returnGoodsLogisticsDate;

    }

    @Data
    @ApiModel("BbcTradeRightsDTO.UpdateETO")
    @Accessors(chain = true)
    public static class UpdateETO extends ETO {
        @ApiModelProperty("售后表ID")
        private String id;
    }

    @Data
    @ApiModel("BbcTradeRightsDTO.RevocationTradeRightsETO")
    @Accessors(chain = true)
    public static class RevocationTradeRightsETO extends BaseDTO {
        @ApiModelProperty("售后表ID")
        private String id;
    }

    @Data
    @ApiModel("BbcTradeRightsDTO.GoodsTotalDTO")
    @Accessors(chain = true)
    public static class GoodsTotalDTO extends BaseDTO {

        @ApiModelProperty("当前订单id")
        private String tradeId;

        @ApiModelProperty("商品sku id")
        private List<String> skuIds;
    }
}
