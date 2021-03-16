package com.gs.lshly.common.struct.bbc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-05
*/
public abstract class BbcShopNavigationMenuVO implements Serializable {

    @Data
    @ApiModel("BbcShopNavigationMenuVO.MenuListVO")
    @Accessors(chain = true)
    public static class MenuListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("菜单图片")
        private String menuImage;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;
    }

    @Data
    @ApiModel("BbcShopNavigationMenuVO.TextMenuVO")
    @Accessors(chain = true)
    public static class TextMenuVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;
    }
}

