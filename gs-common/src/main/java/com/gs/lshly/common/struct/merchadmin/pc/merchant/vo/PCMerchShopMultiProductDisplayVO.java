package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author 陈奇
* @since 2020-10-23
*/
public abstract class PCMerchShopMultiProductDisplayVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("栏目名称")
        private String columuName;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopMultiProductDisplayVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable {
        @ApiModelProperty("id")
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
}
