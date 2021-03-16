package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
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
public abstract class PCMerchShopNavigationVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "创建时间",position = 5)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "子分类节点数组",position = 6)
        List<PCMerchShopNavigationVO.ChildVO> childList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchShopNavigationVO.ChildVO")
    @NoArgsConstructor
    public static class ChildVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "创建时间",position = 5)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "父层级ID",position = 4)
        private String parentId;
    }


    @Data
    @ApiModel("PCMerchShopNavigationVO.MenuListVO")
    @Accessors(chain = true)
    public static class MenuListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单",position = 3)
        private Integer isMenu;

        @ApiModelProperty(value = "子分类节点数组",position = 6)
        List<PCMerchShopNavigationVO.MenuChildItemVO> childList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchShopNavigationVO.MenuChildItemVO")
    @NoArgsConstructor
    public static class MenuChildItemVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单",position = 3)
        private Integer isMenu;

        @ApiModelProperty(value = "父层级ID",position = 4)
        private String parentId;
    }

    @Data
    @ApiModel("PCMerchShopNavigationVO.InnerListVO")
    @NoArgsConstructor
    public static class  InnerListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "创建时间",position = 5)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "父层级ID",position = 4)
        private String parentId;

        @ApiModelProperty(value = "子节点数据",position = 4)
        private List<InnerChildVO> childList;

    }

    @Data
    @ApiModel("PCMerchShopNavigationVO.InnerChild")
    @NoArgsConstructor
    public static class InnerChildVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "创建时间",position = 5)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "父层级ID",position = 4)
        private String parentId;

    }


    @Data
    @ApiModel("PCMerchShopNavigationVO.SimpleVO")
    @Accessors(chain = true)
    public static class SimpleVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;

        @ApiModelProperty(value = "子分类节点数组",position = 3)
        List<PCMerchShopNavigationVO.SimpleChildVO> childList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationVO.NavigationVO")
    @Accessors(chain = true)
    public static class SimpleChildVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单",position = 4)
        private Integer isMenu;
    }


    @Data
    @ApiModel("PCMerchShopNavigationVO.NavigationVO")
    @Accessors(chain = true)
    public static class NavigationVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;
    }
}
