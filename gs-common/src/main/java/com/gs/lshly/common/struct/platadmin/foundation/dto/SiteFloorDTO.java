package com.gs.lshly.common.struct.platadmin.foundation.dto;
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
* @since 2020-09-30
*/
public abstract class SiteFloorDTO implements Serializable {

    @Data
    @ApiModel("SiteFloorDTO.H5ETO")
    @Accessors(chain = true)
    public static class H5ETO extends BaseDTO {

        @ApiModelProperty(value = "楼层数组")
        private List<H5SiteFloorItem> floorList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;

    }


    @Data
    @ApiModel("SiteFloorDTO.SiteFloorH5Item")
    @Accessors(chain = true)
    public static class H5SiteFloorItem implements Serializable {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("最大商品显示数量")
        private Integer goodsMax;

        @ApiModelProperty("楼层广告图")
        private String topImage;

        @ApiModelProperty("图片跳转链接")
        private String jumpUrl;

        @ApiModelProperty("楼层商品")
        private List<String> goodsIdList;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

    }

    @Data
    @ApiModel("SiteFloorDTO.PCETO")
    @Accessors(chain = true)
    public static class PCETO extends BaseDTO {

        @ApiModelProperty(value = "楼层数组")
        private List<PCSiteFloor> floorList;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

    }

    @Data
    @ApiModel("SiteFloorDTO.PCSiteFloorItem")
    @Accessors(chain = true)
    public static class PCSiteFloor implements Serializable {

        @ApiModelProperty(value = "楼层ID",hidden = true)
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "楼层图标")
        private String icon;

        @ApiModelProperty("楼层左侧大图")
        private String leftImage;

        @ApiModelProperty("楼层菜单数组")
        private List<PCFloorMenu> floorMenuList;

        @ApiModelProperty("楼层链接数组")
        private List<PCFloorLink> floorLinkList;

    }

    @Data
    @ApiModel("SiteFloorDTO.PCFloorMenu")
    @Accessors(chain = true)
    public static class PCFloorMenu implements Serializable {

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "菜单商品数组")
        private List<String> menuGoodsList;


    }

    @Data
    @ApiModel("SiteFloorDTO.PCFloorLink")
    @Accessors(chain = true)
    public static class PCFloorLink implements Serializable {

        @ApiModelProperty("底部链接名称")
        private String menuName;

        @ApiModelProperty("底部链接地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }


}
