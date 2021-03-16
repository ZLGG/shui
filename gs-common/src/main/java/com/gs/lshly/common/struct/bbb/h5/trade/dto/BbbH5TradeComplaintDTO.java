package com.gs.lshly.common.struct.bbb.h5.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-12-23
*/
public abstract class BbbH5TradeComplaintDTO implements Serializable {

    @Data
    @ApiModel("BbbH5TradeComplaintDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "投诉id",hidden = true)
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("订单skuID")
        private String skuId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("投诉类型（10=商品问题 20=配送问题 30=支付问题 40=促销活动问题 50=账户问题 60=发票问题 70=系统问题 80=退货/换货问题 90=表扬/投诉工作人员 100=其他）")
        private Integer complaintType;

        @ApiModelProperty("联系方式")
        private String contactWay;

        @ApiModelProperty("问题描述")
        private String problemDesc;

    }
    @Data
    @ApiModel("BbbH5TradeComplaintDTO.DetailEto")
    public static class DetailEto extends ETO {

        @ApiModelProperty(value = "图片凭证列表")
        private List<String> imageList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbbH5TradeComplaintDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "投诉id")
        private String id;
    }

    @Data
    @ApiModel("BbbH5TradeComplaintDTO.CancelIdeaDTO")
    public static class CancelIdeaDTO extends BaseDTO {

        @ApiModelProperty("投诉id")
        private String id;

        @ApiModelProperty(value = "撤诉理由")
        private String cancelIdea;
    }


}
