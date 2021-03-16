package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-18
*/
public abstract class PCMerchShopNavigationMenuDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.H5ETO")
    @Accessors(chain = true)
    public static class H5ETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "菜单数组")
        private List<H5ItemETO> itemList;

        @ApiModelProperty(value = "删除数据")
        private List<String> removeList;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.H5ItemETO")
    @Accessors(chain = true)
    public static class H5ItemETO implements Serializable {

        @ApiModelProperty(value = "菜单ID")
        private String id;

        @ApiModelProperty(value = "菜单图片")
        private String menuImage;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "是否新增")
        private Integer isNew;

    }


    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.H5TextMenuETO")
    @Accessors(chain = true)
    public static class H5TextMenuETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "文本导航数组")
        private List<H5TextMenuItemETO> itemList;

        @ApiModelProperty(value = "删除数据")
        private List<String> removeList;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.H5TextMenuItemETO")
    @Accessors(chain = true)
    public static class H5TextMenuItemETO implements Serializable {

        @ApiModelProperty(value = "文本导航ID")
        private String id;

        @ApiModelProperty(value = "文本导航名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "是否新增")
        private Integer isNew;

    }


    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.PCETO")
    @Accessors(chain = true)
    public static class PCETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "菜单数组")
        private List<PCItemETO> itemList;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.PCItemETO")
    @Accessors(chain = true)
    public static class PCItemETO implements Serializable {

        @ApiModelProperty(value = "菜单ID")
        private String id;

        @ApiModelProperty(value = "菜单图片")
        private String menuImage;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.PCShopMenuETO")
    @Accessors(chain = true)
    public static class PCShopMenuETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "店铺分类ID数组(二级)")
        private List<String> navMenuList;

        @ApiModelProperty(value = "文本导航数组")
        private List<PCTextMenuETO> textMenuList;

    }


    @Data
    @ApiModel("PCMerchShopNavigationMenuDTO.PCTextMenuETO")
    @Accessors(chain = true)
    public static class PCTextMenuETO implements Serializable {

        @ApiModelProperty(value = "文本导航名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

}
