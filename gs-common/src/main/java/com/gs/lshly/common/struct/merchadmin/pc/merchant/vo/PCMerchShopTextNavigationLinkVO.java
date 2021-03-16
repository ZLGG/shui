package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopTextNavigationLinkVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("文本导航name")
        private String textNavigationName;


        @ApiModelProperty("文本导航链接")
        private String textNavigationLink;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


    }

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkVO.NavigationMenuListVO")
    public static class NavigationMenuListVO implements Serializable {
        @ApiModelProperty(value = "店铺分类列表")
        List<PCMerchShopNavigationVO.MenuListVO> shopNavigations;

        @ApiModelProperty(value = "自定义导航分类列表")
        List<PCMerchShopTextNavigationLinkVO.ListVO> listVOS;

    }

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
