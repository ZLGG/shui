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
* @author 大飞船
* @since 2020-09-29
*/
public abstract class SiteNavigationDTO implements Serializable {

    @Data
    @ApiModel("SiteNavigationDTO.H5DTO")
    @Accessors(chain = true)
    public static class H5DTO extends BaseDTO {

        @ApiModelProperty("菜单导航配置")
        private List<H5Item> navigationList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;

    }

    @Data
    @ApiModel("SiteNavigationDTO.PCDTO")
    @Accessors(chain = true)
    public static class PCDTO extends BaseDTO {

        @ApiModelProperty("菜单导航配置")
        private List<PCItem> navigationList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;

    }

    @Data
    @ApiModel("SiteNavigationDTO.PC2DTO")
    @Accessors(chain = true)
    public static class PC2DTO extends BaseDTO {

        @ApiModelProperty(value = "导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("导航配置")
        private List<PC2Item> navigationList;

    }

    @Data
    @ApiModel("SiteNavigationDTO.H5Item")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class H5Item extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("导航图片")
        private String hotImageUrl;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("SiteNavigationDTO.PCItem")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class PCItem extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("导航图片")
        private String hotImageUrl;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("SiteNavigationDTO.PC2Item")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class PC2Item extends BaseDTO {

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;
    }

}
