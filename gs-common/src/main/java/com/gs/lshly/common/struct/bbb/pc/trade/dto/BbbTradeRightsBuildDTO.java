package com.gs.lshly.common.struct.bbb.pc.trade.dto;

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
public abstract class BbbTradeRightsBuildDTO implements Serializable {

    @Data
    @ApiModel("BbbTradeRightsBuildDTO.ETO")
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
        @ApiModel(value = "BbbTradeRightsBuildDTO.ProductData")
        public static class ProductData implements Serializable {

            @ApiModelProperty(value = "订单商品ID")
            private String tradeGoodsId;

            @ApiModelProperty(value = "退货数量")
            private Integer quantity;
        }

        @Data
        @ApiModel(value = "BbbTradeRightsBuildDTO.RightsImgData")
        public static class RightsImgData  extends BaseDTO implements Serializable {

            @ApiModelProperty("凭证图片")
            private String rightsImg;

        }

    }

    @Data
    @ApiModel("BbbTradeRightsBuildDTO.AddAddressDTO")
    @Accessors(chain = true)
    public static class AddAddressDTO extends BaseDTO {
        @ApiModelProperty("售后表ID")
        private String id;

        @ApiModelProperty("退货物流公司名字")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;
    }
    @Data
    @ApiModel("BbbTradeRightsBuildDTO.ApplyAgain")
    @Accessors(chain = true)
    public static class ApplyAgain extends BaseDTO {

        @ApiModelProperty("售后表ID")
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;
    }


    }
