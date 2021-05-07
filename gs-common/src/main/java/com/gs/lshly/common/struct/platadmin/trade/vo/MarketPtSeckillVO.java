package com.gs.lshly.common.struct.platadmin.trade.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月7日 上午10:38:40
 */
@SuppressWarnings("serial")
public class MarketPtSeckillVO implements Serializable {

    @Data
    @ApiModel("MarketPtSeckillVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private String typeCode;

        @ApiModelProperty("折扣范围")
        private String discountRange;

        @ApiModelProperty("是否提醒[10=是 20=否]")
        private Integer smsIsTell;

        @ApiModelProperty("提醒方式")
        private String reminders;

        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;


        @ApiModelProperty("报名开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signStartTime;


        @ApiModelProperty("报名结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signEndTime;


        @ApiModelProperty("秒杀上线时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime onlineStartTime;


        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillStartTime;


        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer goodsMax;


        @ApiModelProperty("秒杀封面图")
        private String coverImage;


    }
    @Data
    @ApiModel("MarketPtSeckillVO.updateDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateDTO implements Serializable {
        /**
         * 配置秒杀表id
         */
        @ApiModelProperty(value = "配置秒杀表id")
        private String id;

        /**
         * pc端满减
         */
        @ApiModelProperty(value = "pc端满减")
        private Integer pcCut;

        /**
         * pc端满赠
         */
        @ApiModelProperty(value = "pc端满赠")
        private Integer pcGift;

        /**
         * pc端团购
         */
        @ApiModelProperty(value = "pc端团购")
        private Integer pcGroupbuy;

        /**
         * pc端满折
         */
        @ApiModelProperty(value = "pc端满折")
        private Integer pcDiscount;

        /**
         * h5端满减
         */
        @ApiModelProperty(value = "h5端满减")
        private Integer h5Cut;

        /**
         * h5端满赠
         */
        @ApiModelProperty(value = "h5端满赠")
        private Integer h5Gift;

        /**
         * h5端团购
         */
        @ApiModelProperty(value = "h5端团购")
        private Integer h5Groupbuy;

        /**
         * h5端满折
         */
        @ApiModelProperty(value = "h5端满折")
        private Integer h5Discount;

    }

    @Data
    @ApiModel("MarketPtSeckillVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("类目")
        private List<GoodsCategoryVO.CategoryJoinSearchVO> category;
    }

    @Data
    @ApiModel("MarketPtSeckillVO.MerchantSeckill")
    public static class MerchantSeckill implements Serializable {
        @ApiModelProperty("秒杀id")
        private String id;


        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("秒杀类型")
        private String label;

        @ApiModelProperty("报名时间")
        private String signTime;


        @ApiModelProperty("秒杀时间时间")
        private String seckillTime;

        @ApiModelProperty("参与情况 [10=不可参与 20=可参与未报名 30=可参与已报名 40=报名已结束未报名 50=报名已结束已报名 60=未开始报名]")
        private String Participation;

    }

    /**
     * 平台审核中查看后的秒杀规则vo
     */
    @Data
    @ApiModel("MarketPtSeckillVO.CheckSeckill")
    public static class CheckSeckill implements Serializable {
        @ApiModelProperty("秒杀id")
        private String id;

        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("发布时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime onlineStartTime;

        @ApiModelProperty("报名开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signStartTime;


        @ApiModelProperty("报名结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signEndTime;


        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillStartTime;


        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("可报名店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private String typeCode;

        @ApiModelProperty("参加秒杀的类目")
        private String seckillCategory;

        @ApiModelProperty("参加秒杀的类目名字")
        private List<MarketPtSeckillVO.CategoryJoinSearchVO>  seckillCategoryName;

        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer goodsMax;

        @ApiModelProperty("商品折扣范围")
        private String discountRange;
    }
    @Data
    @ApiModel("MarketPtSeckillVO.CategoryJoinSearchVO")
    public static class CategoryJoinSearchVO implements Serializable{
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;
    }

    /**
     * 商家秒杀报名——》操作——》查看详情
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("MarketPtSeckillVO.MerchantViewDetails")
    public static class MerchantViewDetails implements Serializable {
        @ApiModelProperty("秒杀信息")
        private MarketPtSeckillVO.CheckSeckill seckillInfo;
        @ApiModelProperty("参加秒杀的商品")
        private List<MarketPtSeckillVO.SeckillGoods> seckillGoodsList;


    }

    /**
     * 商家秒杀报名——》操作——》查看详情-->参加秒杀的商品
     */
    @Data
    @Accessors(chain = true)
    @ApiModel("MarketPtSeckillVO.SeckillGoods")
    public static class SeckillGoods implements Serializable {
        @ApiModelProperty("秒杀商品参加记录id")
        private String id;

        @ApiModelProperty("商品名字")
        private String name;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("原价")
        private BigDecimal salePrice;

        @ApiModelProperty("促销价")
        private BigDecimal seckillSalePrice;

        @ApiModelProperty("Sku信息")
        private List<MarketPtSeckillVO.SeckillGoodsSku> skuInfo;
    }
    /**
     * 商家秒杀报名——》操作——》查看详情-->参加秒杀的商品-->查看sku
     * */
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("MarketPtSeckillVO.SeckillGoodsSku")
    public static class SeckillGoodsSku implements Serializable {
        @ApiModelProperty("秒杀商品SKU参加记录id")
        private String id;

        @ApiModelProperty("商品名字")
        private String name;

        @ApiModelProperty("规格值组")
        private String specsValue;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("促销价")
        private BigDecimal seckillSaleSkuPrice;
    }

}
