package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 大飞船
* @since 2020-09-29
*/
public abstract class SiteFloorMenuDTO implements Serializable {

    @Data
    @ApiModel("SiteFloorMenuDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty("菜单类型[10=楼层顶部 20=左侧链接]")
        private Integer menuType;

        @ApiModelProperty("链接地址")
        private String jumpUrl;

        @ApiModelProperty("开窗类型[10=当前 20=新窗]")
        private Integer openType;

        @ApiModelProperty("排序")
        private Integer idx;
    }



    @Data
    @ApiModel("SiteFloorMenuDTO.ADTO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty(value = "菜单类型[10=楼层顶部 20=左侧链接]",hidden = true)
        private Integer menuType;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuDTO.UDTO")
    @Accessors(chain = true)
    public static class UDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "楼层id")
        private String floorId;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty(value = "菜单类型[10=楼层顶部 20=左侧链接]",hidden = true)
        private Integer menuType;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuDTO.BADTO")
    @Accessors(chain = true)
    public static class BADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty("链接地址")
        private String jumpUrl;

        @ApiModelProperty("开窗类型[10=当前 20=新窗]")
        private Integer openType;

        @ApiModelProperty(value = "菜单类型[10=楼层顶部 20=左侧链接]",hidden = true)
        private Integer menuType;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuDTO.UDTO")
    @Accessors(chain = true)
    public static class BUDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty("链接地址")
        private String jumpUrl;

        @ApiModelProperty("开窗类型[10=当前 20=新窗]")
        private Integer openType;

        @ApiModelProperty(value = "菜单类型[10=楼层顶部 20=左侧链接]",hidden = true)
        private Integer menuType;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
}
