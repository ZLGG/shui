package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Starry
 * @Date 16:48 2020/10/19
 */
public abstract class PCMerchGoodsCategoryVO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchGoodsCategoryVO.ListVO")
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别父id")
        private String parentId;

        @ApiModelProperty(value = "商品类别级别")
        private Integer gsCategoryLevel;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchGoodsCategoryVO.CategoryTreeVO")
    public static class CategoryTreeVO extends ListVO {
        @ApiModelProperty("使用平台")
        private Integer useFiled;

        @ApiModelProperty("子分类")
        private List<CategoryTreeVO> list;
    }

    @Data
    @ApiModel("PCMerchGoodsCategoryVO.innerCategoryVO")
    public static class innerCategoryVO extends ListVO {
        @ApiModelProperty("使用平台")
        private Integer useFiled;

    }
}
