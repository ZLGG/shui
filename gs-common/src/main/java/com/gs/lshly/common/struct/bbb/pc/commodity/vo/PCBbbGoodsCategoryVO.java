package com.gs.lshly.common.struct.bbb.pc.commodity.vo;

import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class PCBbbGoodsCategoryVO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品类别id")
        private String id;

        @ApiModelProperty("商品类别名称")
        private String gsCategoryName;

        @ApiModelProperty("商品类别父id")
        private String parentId;

        @ApiModelProperty("商品类别级别")
        private Integer gsCategoryLevel;

        @ApiModelProperty("商品类别logo")
        private String gsCategoryLogo;

        @ApiModelProperty("商品类别服务费率(只有三级有)")
        private BigDecimal gsCategoryFee;

        @ApiModelProperty("商品类别平台使用费(只有一级有)")
        private BigDecimal gsCategoryMoney;

        @ApiModelProperty("默认展示模板")
        private Integer showType;

        @ApiModelProperty("商品类别显示区域")
        private Integer useFiled;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("操作人")
        private String operator;

    }

    @Data
    @ApiModel("PCBbbGoodsCategoryVO.CategoryTreeVO")
    public static class CategoryTreeVO extends ListVO {
        @ApiModelProperty("子分类列表")
        private List<CategoryTreeVO> list;
    }


    @Data
    @ApiModel("PCBbbGoodsCategoryVO.CategoryMenuVO")
    @Accessors(chain = true)
    public static class CategoryMenuVO implements Serializable {

        @ApiModelProperty("首页配置信息")
        private BbbSiteNavigationVO.HomeVO homeSettins;

        @ApiModelProperty("商品分类列表")
        private List<CategoryTreeVO> categoryTreeVOS = new ArrayList<>();

    }


    @Data
    @ApiModel("PCBbbGoodsCategoryVO.CategoryNavigationVO")
    @Accessors(chain = true)
    public static class CategoryNavigationVO implements Serializable {

        @ApiModelProperty("三级类目列表")
        List<ListVO> categoryLevel3List = new ArrayList<>();

        @ApiModelProperty("类目关联的品牌列表")
        List<PCBbbGoodsBrandVO.ListVO> allBrandVOs = new ArrayList<>();

    }

    @Data
    @ApiModel("PCBbbGoodsCategoryVO.CategoryForBrandVO")
    @Accessors(chain = true)
    public static class CategoryForBrandVO implements Serializable {

        @ApiModelProperty("品牌ID")
        private String brandId;

        @ApiModelProperty("三级类目列表")
        List<ListVO> categoryLevel3List = new ArrayList<>();

    }

}
