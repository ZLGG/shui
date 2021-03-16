package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 15:26 2020/9/27
 */
public abstract class GoodsCategoryVO implements Serializable {
    @Data
    @Accessors(chain = true)
    @ApiModel("类目管理")
    public static class ListVO implements Serializable{
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别父id")
        private String parentId;

        @ApiModelProperty(value = "商品类别级别")
        private Integer gsCategoryLevel;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;

        @ApiModelProperty(value = "商品类别logo")
        private String gsCategoryLogo;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "显示范围")
        private Integer useFiled;


    }

    @Data
    @Accessors(chain = true)
    public static class ParentCategoryVO implements Serializable{
        @ApiModelProperty(value = "当前类目id")
        private String lev;

        @ApiModelProperty(value = "当前类目名称")
        private String levName;

        @ApiModelProperty(value = "父一级类目id")
        private String lev1Id;

        @ApiModelProperty(value = "父二级类目id")
        private String lev2Id;

        @ApiModelProperty(value = "父一级类目名称")
        private String lev1Name;

        @ApiModelProperty(value = "父二级类目名称")
        private String lev2Name;
    }

    @Data
    public static class DetailVO extends ListVO{

        @ApiModelProperty(value = "默认展示模式")
        private Integer showType;

    }

    @Data
    public static class Level3VO extends GoodsCategoryVO.DetailVO {
        @ApiModelProperty(value = "商品类别服务费率(只有三级有)")
        private BigDecimal gsCategoryFee;

    }

    @Data
    public static class Level2VO extends GoodsCategoryVO.DetailVO {

    }

    @Data
    public static class Level1VO extends GoodsCategoryVO.DetailVO {
        @ApiModelProperty(value = "商品类别平台使用费(只有一级有)")
        private BigDecimal gsCategoryMoney;
    }

    @Data
    public static class CategoryTreeVO extends ListVO{


        @ApiModelProperty(value = "商品类别平台使用费")
        private BigDecimal gsCategoryMoney;

        @ApiModelProperty(value = "商品类别服务费率")
        private BigDecimal gsCategoryFee;

        @ApiModelProperty("子分类")
        private List<GoodsCategoryVO.CategoryTreeVO> list = new ArrayList<>();
    }

    @Data
    public static class CategoryExcelVO extends ListVO{
        @ApiModelProperty(value = "一级分类",position = 1)
        private String level1Name;

        @ApiModelProperty(value = "二级分类",position = 2)
        private String level2Name;

        @ApiModelProperty(value = "三级分类",position = 3)
        private String level3Name;

        @ApiModelProperty(value = "类目费率",position = 4)
        private String fee;

        @ApiModelProperty(value = "平台使用费（单位：元/年）",position = 5)
        private String money;
    }

    @Data
    public static class CategoryJoinSearchVO implements Serializable{
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;
    }

}
