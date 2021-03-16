package com.gs.lshly.common.struct.bbc.trade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

        @ApiModelProperty("申请售后原因")
        private Integer rightsReasonType;

        @ApiModelProperty("申请售后说明")
        private String rightsRemark;

        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;

        @ApiModelProperty(value = "订单商品信息")
        private List<ProductData> productData;

        @ApiModelProperty(value = "凭证图片集合")
        private List<RightsImgData> rightsImgData;

        @Data
        public static class ProductData implements Serializable {

            @ApiModelProperty(value = "订单商品ID")
            private String tradeGoodsId;

            @ApiModelProperty(value = "退货数量")
            private Integer quantity;
        }

        @Data
        public static class RightsImgData  extends BaseDTO implements Serializable {

            @ApiModelProperty("凭证图片")
            private String rightsImg;

        }

    }
        @Data
        public static class RightsReturnGoodsLogistics  extends BaseDTO implements Serializable {

            @ApiModelProperty("售后单ID")
            private String rightsId;

            @ApiModelProperty("退货物流公司名称")
            private String returnGoodsLogisticsName;

            @ApiModelProperty("退货物流单号")
            private String returnGoodsLogisticsNum;

            @ApiModelProperty("退货寄出时间")
            @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            private LocalDateTime returnGoodsLogisticsDate;

        }

}
