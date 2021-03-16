package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-23
*/
public abstract class PCMerchShopBannerDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopBannerDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("轮播图导航id")
        private String bannerNavigationId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopBannerDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchShopBannerDTO.H5ETO")
    @Accessors(chain = true)
    public static class H5ETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "轮播图数组")
        private List<H5ItemETO> itemList;

        @ApiModelProperty(value = "删除数据")
        private List<String> removeList;
    }

    @Data
    @ApiModel("PCMerchShopBannerDTO.H5ItemETO")
    @Accessors(chain = true)
    public static class H5ItemETO extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增[0=否 1=是]")
        private Integer isNew;

    }

    @Data
    @Accessors(chain = true)
    public static class PCETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "轮播图数组")
        private List<PCBanner> bannerList;

        @ApiModelProperty(value = "删除数据")
        private List<String> removeList;
    }

    @Data
    @ApiModel("PCMerchShopBannerDTO.PCBanner")
    @Accessors(chain = true)
    public static class PCBanner extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增[0=否 1=是]")
        private Integer isNew;

    }

}
