package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-06
*/
public abstract class PCMerchMerchantUserTypeRatioVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeRatioVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("会员类型id")
        private String userTypeId;


        @ApiModelProperty("商品id")
        private String goodsId;


        @ApiModelProperty("商品sku_id")
        private String skuId;


        @ApiModelProperty("店铺id")
        private String shopId;




    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeRatioVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
