package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
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
* @author zdf
* @since 2020-12-10
*/
public abstract class PCMerchMarketMerchantGroupbuyDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("团购活动名称")
        private String groupbuyName;

        @ApiModelProperty("描述")
        private String groupbuyDescribe;

        @ApiModelProperty("适用平台[10=2b 20=2c]适用平台[10=pc端 20=wap端 30=app端]（CardTerminalTypeEnum）")
        private String terminal;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("参数与次数")
        private Integer userDoNumber;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("平台审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("逻辑提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("逻辑取消标记")
        private Boolean isCancel;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyDTO.AddDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddDTO extends ETO {
        private List<SignGoods> goodsList;
        private List<SignGoodsSku> goodsSPUAll;
    }
    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyDTO.SignGoods")
    @AllArgsConstructor
    public static class SignGoods implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String  goodsId;

        @ApiModelProperty(value = "原价")
        private BigDecimal originPrice;


    }
    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyDTO.SignGoodsSku")
    @AllArgsConstructor
    public static class SignGoodsSku implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String goodsId ;

        @ApiModelProperty(value = "商家报名SPUID")
        private String goodsSpuItemId;

        @ApiModelProperty(value = "skuID")
        private String skuId;

        @ApiModelProperty(value = "原价")
        private BigDecimal originPrice;

        @ApiModelProperty(value = "活动价")
        private BigDecimal activitySalePrice;
    }


}
