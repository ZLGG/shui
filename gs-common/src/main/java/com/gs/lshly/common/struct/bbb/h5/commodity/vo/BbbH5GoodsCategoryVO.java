package com.gs.lshly.common.struct.bbb.h5.commodity.vo;

import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
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
public abstract class BbbH5GoodsCategoryVO implements Serializable {

    @Data
    @ApiModel("BbbH5GoodsCategoryVO.ListVO")
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
    @ApiModel("BbbH5GoodsCategoryVO.CategoryTreeVO")
    public static class CategoryTreeVO extends ListVO {
        @ApiModelProperty("子分类列表")
        private List<CategoryTreeVO> list;

        @ApiModelProperty("站点分类广告图")
        private List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> categoryAdvertListVO = new ArrayList<>();

    }


}
