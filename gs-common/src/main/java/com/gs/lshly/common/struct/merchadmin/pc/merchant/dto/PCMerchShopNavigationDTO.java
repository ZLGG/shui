package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-18
*/
public abstract class PCMerchShopNavigationDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("导航分类名")
        private String navName;

        @ApiModelProperty("层级[1-2]")
        private Integer leve;

        @ApiModelProperty("父ID")
        private String parentId;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.IdListDTO")
    @NoArgsConstructor
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private List<String> idList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.ItemListDTO")
    @NoArgsConstructor
    public static class ItemListDTO extends BaseDTO {

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "分类项数组22")
        private List<ItemDTO> itemList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.ItemDTO")
    @NoArgsConstructor
    public static class ItemDTO extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否新增[0=否 1=是]",position = 3)
        private Integer isNew;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "父层级ID",position = 5)
        private String parentId;

        @ApiModelProperty(value = "子分类节点数组",position = 6)
        List<ChildItemDTO> childList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.ChildItemDTO")
    @NoArgsConstructor
    public static class ChildItemDTO extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否新增[0=否 1=是]",position = 3)
        private Integer isNew;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "父层级ID",position = 5)
        private String parentId;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.DeleteDTO")
    @NoArgsConstructor
    public static class DeleteDTO extends BaseDTO{

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "ID数组")
        private List<String> idList;

    }


    @Data
    @ApiModel("PCMerchShopNavigationDTO.ToMenuListDTO")
    @NoArgsConstructor
    public static class ToMenuListDTO extends BaseDTO {

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "店铺分类ID数组")
        private List<String> itemList;
    }


    @Data
    @ApiModel("PCMerchShopNavigationDTO.MenuItemListDTO")
    @NoArgsConstructor
    public static class MenuListDTO extends BaseDTO {

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "店铺分类ID数组")
        private List<MenuItemDTO> itemList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.MenuItemDTO")
    @NoArgsConstructor
    public static class MenuItemDTO extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单",position = 2)
        private Integer isMenu;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "父层级ID",position = 5)
        private String parentId;

        @ApiModelProperty(value = "子分类节点数组",position = 6)
        List<MenuChildItemDTO> childList;
    }

    @Data
    @ApiModel("PCMerchShopNavigationDTO.MenuChildItemDTO")
    @NoArgsConstructor
    public static class MenuChildItemDTO extends BaseDTO{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "是否导航菜单",position = 2)
        private Integer isMenu;

        @ApiModelProperty(value = "排序",position = 4)
        private Integer idx;

        @ApiModelProperty(value = "父层级ID",position = 5)
        private String parentId;
    }


    @Data
    @ApiModel("PCMerchShopNavigationDTO.InnerListDTO")
    @NoArgsConstructor
    public static class InnerListDTO  extends BaseDTO{

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

    }
}