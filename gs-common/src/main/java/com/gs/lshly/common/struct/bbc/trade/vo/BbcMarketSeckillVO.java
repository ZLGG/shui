package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:13:10
 */
@SuppressWarnings("serial")
public abstract class BbcMarketSeckillVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询商品参加了哪个秒杀人活动
	 *
	 * 
	 * @author yingjun
	 * @date 2021年5月23日 上午1:13:41
	 */
	@Data
    @ApiModel("BbcMarketSeckillVO.GoodsVO")
    @Accessors(chain = true)
    public static class GoodsVO implements Serializable {
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
		private LocalDateTime seckillStartTime;

		@ApiModelProperty("结束秒杀时间")
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime seckillEndTime;
	}

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
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcMarketSeckillVO.SeckillGoodsVO")
    @Accessors(chain = true)
    public static class SeckillGoodsVO extends ListVO{

		@ApiModelProperty("秒杀id")
		private String seckillId;

		@ApiModelProperty("秒杀价->复用商城首页")
        private BigDecimal salePrice;
		
        @ApiModelProperty("秒杀价")
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
	
	/**
	 * 积分商城秒杀活动列表
	 *
	 * 
	 * @author yingjun
	 * @date 2021年4月27日 上午9:56:29
	 */
	@Data
	@ApiModel("BbcMarketActivityVO.SeckillPointHome")
	@Accessors(chain = true)
	public static class SeckillPointHome implements Serializable {
		
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
		private LocalDateTime seckillStartTime;

		@ApiModelProperty("结束秒杀时间")
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime seckillEndTime;

		@ApiModelProperty("商品列表")
		private List<BbcGoodsInfoVO.DetailVO> goodsList;
	}
	
	@Data
	@ApiModel("BbcMarketActivityVO.HomePageSeckill")
	@Accessors(chain = true)
	public static class HomePageSeckill implements Serializable {
		
		@ApiModelProperty("id")
		private String id;

		@ApiModelProperty("状态 10抢购中 20 已开抢 30 昨日已开抢 40 即将开抢")
		private Integer status;

		@ApiModelProperty("状态描述")
		private String statusText;
		
		@ApiModelProperty("时间段")
		private String name;
		
		@ApiModelProperty("商品列表")
		private List<HomePageSeckillGoods> goodsList;
	}
	
	@Data
	@ApiModel("BbcMarketActivityVO.HomePageSeckillGoods")
	@Accessors(chain = true)
	public static class HomePageSeckillGoods implements Serializable {

		@ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("默认第一个sku商品id")
        private String skuId;
        
        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;
        
        @ApiModelProperty("原价")
        private BigDecimal oldPrice;
        
        @ApiModelProperty("销售价")
        private BigDecimal salePrice;
        
        @ApiModelProperty("积分原价")
        private BigDecimal pointPrice;
        
        @ApiModelProperty("原积分原价")
        private BigDecimal oldPointPrice;
        
        @ApiModelProperty("是否是积分商品")
        private BigDecimal isPointGood;

        @ApiModelProperty("销售量")
        private BigDecimal saleRate;
        
        @ApiModelProperty("备注信息")
        private String remark = "100天历史最低价";
	}
}
