package com.gs.lshly.common.struct.bbc.commodity.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author Starry
* @since 2020-10-23
*/
@SuppressWarnings("serial")
public abstract class BbcGoodsCategoryVO implements Serializable {

    @Data
    @ApiModel("BbcGoodsCategoryVO.ListVO")
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
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryVO.CategoryTreeVO")
    public static class CategoryTreeVO extends ListVO {
        @ApiModelProperty("子分类列表")
        private List<CategoryTreeVO> list;

        @ApiModelProperty("站点分类广告图")
        private List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> categoryAdvertListVO = new ArrayList<>();

    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

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
    
    
    /**
     * 电信产品首页
     *
     * 
     * @author yingjun
     * @date 2021年4月25日 上午11:49:09
     */
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryVO.CtccHomeVO")
    public static class CtccHomeVO implements Serializable {
    	
    	@ApiModelProperty("广告位列表")
		private List<CtccGoodsInfoAdvertVO> adverts;

    	@ApiModelProperty("分类列表")
        private List<CtccCategoryVO> categorys;

    }
    
	@Data
    @ApiModel("BbcGoodsCategoryVO.CtccCategoryVO")
    @Accessors(chain = true)
    public static class CtccCategoryVO implements Serializable{
    	
		@ApiModelProperty("电信产品分类ID")
		private String id;
		
		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("排序")
		private Integer idx;
		
    }
	
    /**
     * 电信产品广告
     *
     * 
     * @author yingjun
     * @date 2021年4月25日 上午11:37:58
     */
	@Data
    @ApiModel("BbcGoodsCategoryVO.CtccGoodsInfoAdvertVO")
    @Accessors(chain = true)
    public static class CtccGoodsInfoAdvertVO implements Serializable{
    	
		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("封面图")
		private String imageUrl;

		@ApiModelProperty("封面图跳转")
		private String jumpUrl;
		
		@ApiModelProperty("是否是类目商品")
		private Integer isCategory;
		
		@ApiModelProperty("类目ID")
		private String categoryId;
		
		@ApiModelProperty("备注")
		private String remark;

	}
}
