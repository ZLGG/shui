package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-18
*/
public abstract class PCMerchShopNavigationMenuVO implements Serializable {


    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable{

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty(value = "菜单图片")
        private String menuImage;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.H5TextMenuListVO")
    @Accessors(chain = true)
    public static class H5TextMenuListVO implements Serializable{

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PcShopMenuVO")
    @Accessors(chain = true)
    public static class PcShopMenuVO implements Serializable{

        @ApiModelProperty(value = "自定义文本导航")
        private List<PcTextMenuVO> textMenuList = new ArrayList<>();

        @ApiModelProperty(value = "店铺自定义分类")
        private List<PcNavMenuVO> navMenuList = new ArrayList<>();
    }
    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PcTextMenuVO")
    @Accessors(chain = true)
    public static class PcTextMenuVO implements Serializable{

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PcNavMenuVO")
    @Accessors(chain = true)
    public static class PcNavMenuVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单[0=否 1=是]",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "排序]",position = 4,hidden = true)
        private Integer idx;

        @ApiModelProperty(value = "二级分类",position = 4)
        private List<PcNavChildMenuVO>  childList = new ArrayList<>();


    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PcNavChildMenuVO")
    @Accessors(chain = true)
    public static class PcNavChildMenuVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单[0=否 1=是]",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "排序]",position = 4,hidden = true)
        private Integer idx;

        @ApiModelProperty(value = "父层级ID",position = 4)
        private String parentId;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty(value = "菜单图片")
        private String menuImage;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuVO.PCTextMenuListVO")
    @Accessors(chain = true)
    public static class PCTextMenuListVO implements Serializable{

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }
}
