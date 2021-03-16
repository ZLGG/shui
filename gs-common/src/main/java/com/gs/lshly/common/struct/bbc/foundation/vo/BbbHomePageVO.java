package com.gs.lshly.common.struct.bbc.foundation.vo;

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
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午4:12:02
 */
public abstract class BbbHomePageVO implements Serializable {

    @Data
    @ApiModel("BbbHomePageVO.ListVO")
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
    @ApiModel("BbbHomePageVO.CategoryTreeVO")
    public static class CategoryTreeVO extends ListVO {
        @ApiModelProperty("子分类列表")
        private List<CategoryTreeVO> list;
    }


    @Data
    @ApiModel("BbbHomePageVO.CategoryMenuVO")
    @Accessors(chain = true)
    public static class CategoryMenuVO implements Serializable {

        @ApiModelProperty("首页配置信息")
        private BbbSiteNavigationVO.HomeVO homeSettins;

        @ApiModelProperty("商品分类列表")
        private List<CategoryTreeVO> categoryTreeVOS = new ArrayList<>();

    }


}
