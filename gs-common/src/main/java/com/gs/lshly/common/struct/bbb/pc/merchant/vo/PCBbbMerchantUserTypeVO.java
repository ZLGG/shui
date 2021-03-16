package com.gs.lshly.common.struct.bbb.pc.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-25
*/
public abstract class PCBbbMerchantUserTypeVO implements Serializable {

    @Data
    @ApiModel("PCBbbMerchantUserTypeVO.DetailsVO")
    @Accessors(chain = true)
    public static class DetailsVO implements Serializable{

        @ApiModelProperty("店铺私域会员类型ID")
        private String userTypeId;

        @ApiModelProperty("会员类型（等级）名")
        private String userTypeName;

        @ApiModelProperty("折扣率")
        private BigDecimal ratio;

        @ApiModelProperty("会员等级对应的折扣商品")
        private List<RatioGoodsVO> ratioGoodsVOS = new ArrayList<>();
    }

    @Data
    @ApiModel("PCBbbMerchantUserTypeVO.RatioGoodsVO")
    public static class RatioGoodsVO implements Serializable{
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("店铺id")
        private String shopId;
    }
}
