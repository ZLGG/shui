package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:13:10
 */
public abstract class BbcMarketSeckillVO implements Serializable {

	/**
	 * 秒杀活动列表
	 *
	 * 
	 * @author yingjun
	 * @date 2021年4月15日 上午10:15:39
	 */
	@Data
    @ApiModel("BbcMarketSeckillVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        
		@ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名字（名字+标题）")
        private String goodsName;

        @ApiModelProperty("图片")
        private String goodsImage;

        @ApiModelProperty("原价")
        private BigDecimal oldPrice;
        
        @ApiModelProperty("商品类型 10普通商品20积分商品IN会员商品")
        private Integer goodsType;
        
        @ApiModelProperty("优惠券列表")
        private List<CouponVO> coupons;
        
        @ApiModelProperty("销售量")
        private BigDecimal saleRate;
    }
	
    @Data
    @ApiModel("BbcMarketSeckillVO.SeckillGoodsVO")
    @Accessors(chain = true)
    public static class SeckillGoodsVO extends ListVO{
        @ApiModelProperty("活动价")
        private BigDecimal seckillPrice;
        
        @ApiModelProperty("积分价")
        private BigDecimal seckillPointPrice;
        
        @ApiModelProperty("IN会员活动价")
        private BigDecimal seckillInMemberPointPrice;
        
    }
    
    @Data
    @ApiModel("BbcMarketSeckillVO.CouponVO")
    @Accessors(chain = true)
    public static class CouponVO implements Serializable {
        @ApiModelProperty("优惠券类型 10满减 ")
        private Integer type;
        
        @ApiModelProperty("优惠券名称")
        private String name;
        
    }
	@Data
	@ApiModel("BbcMarketActivityVO.Seckill")
	@Accessors(chain = true)
	public static class Seckill implements Serializable {
		@ApiModelProperty("id")
		private String id;

		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("备注")
		private String remark;

		@ApiModelProperty("排序")
		private Integer idx;

		@ApiModelProperty("开始秒杀时间")
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime activityStartTime;

		@ApiModelProperty("结束秒杀时间")
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime activityEndTime;

		@ApiModelProperty("商品列表")
		private List<BbcGoodsInfoVO.DetailVO> goodsList;
	}
	
	/**
	 * 秒杀活动首页
	 *
	 * 
	 * @author yingjun
	 * @date 2021年4月14日 下午5:55:28
	 */
	@Data
	@ApiModel("BbcMarketActivityVO.SeckillHome")
	@Accessors(chain = true)
	public static class SeckillHome implements Serializable {

		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("封面图")
		private String imageUrl;

		@ApiModelProperty("封面图跳转")
		private String jumpUrl;

		@ApiModelProperty("秒杀时间段")
		private List<SeckillTimeQuantum> timeQuantum;
		
		@ApiModelProperty("结束秒杀时间")
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime seckillEndTime;
		
	}
	
	@Data
	@ApiModel("BbcMarketActivityVO.SeckillTimeQuantum")
	@Accessors(chain = true)
	public static class SeckillTimeQuantum implements Serializable {
		@ApiModelProperty("id")
		private String id;

		@ApiModelProperty("名称")
		private String name;

		@ApiModelProperty("状态 10抢购中 20 已开抢 30 昨日已开抢 40 即将开抢")
		private Integer status;

		@ApiModelProperty("状态描述")
		private String statusDesc;
		
		@ApiModelProperty("时间段")
		private String timeQuantum;
	}
}
