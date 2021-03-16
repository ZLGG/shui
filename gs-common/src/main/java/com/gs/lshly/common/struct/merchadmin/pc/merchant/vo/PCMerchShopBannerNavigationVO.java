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
public abstract class PCMerchShopBannerNavigationVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopBannerNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("PCMerchShopBannerNavigationVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
