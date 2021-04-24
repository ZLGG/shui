package com.gs.lshly.common.struct.bbc.commodity.vo;
import java.io.Serializable;
import java.util.List;

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
public abstract class BbcCtccCategoryGoodsVO implements Serializable {

	
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
		private List<BbcGoodsInfoVO.DetailVO> goodsList;

    }
	
	
}
