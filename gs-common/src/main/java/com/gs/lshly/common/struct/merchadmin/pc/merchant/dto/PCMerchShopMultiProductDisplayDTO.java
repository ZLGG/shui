package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author 陈奇
* @since 2020-10-23
*/
public abstract class PCMerchShopMultiProductDisplayDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("关键字")
        private String keyword;

        @ApiModelProperty("店铺导航分类id")
        private String navigationId;

        @ApiModelProperty("栏目名称")
        private String columuName;

        @ApiModelProperty("商品数量")
        private Integer goodsNumber;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayDTO.ADTO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("关键字")
        private String keyword;

        @ApiModelProperty("店铺导航分类id")
        private String navigationId;

        @ApiModelProperty("栏目名称")
        private String columuName;

        @ApiModelProperty("商品数量")
        private Integer goodsNumber;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;
    }

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayDTO.UDTO")
    @Accessors(chain = true)
    public static class UDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("关键字")
        private String keyword;

        @ApiModelProperty("店铺导航分类id")
        private String navigationId;

        @ApiModelProperty("栏目名称")
        private String columuName;

        @ApiModelProperty("商品数量")
        private Integer goodsNumber;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;
    }

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayDTO.CDTO")
    @AllArgsConstructor
    public static class CDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("排序")
        private Integer idx;
    }


}
