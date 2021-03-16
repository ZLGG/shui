package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-01
*/
public abstract class PCMerchMarketPtActivityGoodsSpuVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动ID")
        private String activityId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String activityDescribe;
        /**
         * sku活动价
         */
        private BigDecimal activitySalePrice;

        @ApiModelProperty("商品ID")
        private String goodsId;




    }

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
