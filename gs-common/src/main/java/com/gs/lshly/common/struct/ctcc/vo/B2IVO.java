package com.gs.lshly.common.struct.ctcc.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 电信类产品
 *
 * 
 * @author yingjun
 * @date 2021年4月20日 上午10:37:25
 */
@SuppressWarnings("serial")
public abstract class B2IVO implements Serializable {

	
	/**
	 * 电信国际
	 *
	 * 
	 * @author yingjun
	 * @date 2021年4月20日 上午10:41:48
	 */
	@Data
    @ApiModel("BbcCtccCategoryGoodsVO.CtccInternationalHomeVO")
    @Accessors(chain = true)
    public static class CtccInternationalHomeVO implements Serializable{
    	
		@ApiModelProperty("广告位列表")
		private List<CtccInternationalAdvertVO> adverts;

		@ApiModelProperty("国际大牌分类")
		private List<CtccInternationalCategoryVO> categorys;
		
    }
	
	@Data
    @ApiModel("BbcCtccCategoryGoodsVO.CtccInternationalAdvertVO")
    @Accessors(chain = true)
    public static class CtccInternationalAdvertVO implements Serializable{
    	
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
	
	@Data
    @ApiModel("BbcCtccCategoryGoodsVO.CtccInternationalCategoryVO")
    @Accessors(chain = true)
    public static class CtccInternationalCategoryVO implements Serializable{
    	
		@ApiModelProperty("电信国际分类ID")
		private String id;
		
		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("封面图")
		private String imageUrl;
		
		@ApiModelProperty("排序")
		private Integer idx;
		
		@ApiModelProperty("备注")
		private String remark;
		
		@ApiModelProperty("商品列表")
		private List<B2IVO.GoodsListVO> goodsList;

    }
	
	@Data
    @ApiModel("BbcCtccCategoryGoodsVO.GoodsListVO")
    public static class GoodsListVO implements Serializable {
    	
        @ApiModelProperty("标签")
        private List<String> tags;

        @ApiModelProperty("商品id")
        private String goodsId;

        @JsonIgnore
        private String id;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice = BigDecimal.ZERO;

        @ApiModelProperty(value = "商品市场价/原价")
        private BigDecimal oldPrice = BigDecimal.ZERO;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice=BigDecimal.ZERO;

        @ApiModelProperty("原积分价格")
        private BigDecimal oldPointPrice=BigDecimal.ZERO;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood = false;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift = false;

        @ApiModelProperty("IN会员价格")
        private BigDecimal inMemberPointPrice=BigDecimal.ZERO;

        @ApiModelProperty("当前登录的用户ID")
        private String userId;

        @ApiModelProperty("用户类型(1-普通用户 2-电信用户)")
        private Integer memberType = 1;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser = 0;
        
    }
}
