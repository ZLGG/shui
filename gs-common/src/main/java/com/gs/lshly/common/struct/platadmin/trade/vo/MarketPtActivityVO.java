package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-11-28
*/
public class MarketPtActivityVO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String activityDescribe;

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


        @ApiModelProperty("活动上线时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime onlineStartTime;


        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime activityStartTime;


        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime activityEndTime;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer goodsMax;


        @ApiModelProperty("活动封面图")
        private String coverImage;


    }

    @Data
    @ApiModel("MarketPtActivityVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("类目")
        private List<GoodsCategoryVO.CategoryJoinSearchVO> category;
    }

    @Data
    @ApiModel("MarketPtActivityVO.MerchantActivity")
    public static class MerchantActivity implements Serializable {
        @ApiModelProperty("活动id")
        private String id;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("活动类型")
        private String label;

        @ApiModelProperty("报名时间")
        private String signTime;


        @ApiModelProperty("活动时间时间")
        private String activityTime;

        @ApiModelProperty("参与情况 [10=不可参与 20=可参与未报名 30=可参与已报名 40=报名已结束未报名 50=报名已结束已报名 60=未开始报名]")
        private String Participation;

    }

    /**
     * 平台审核中查看后的活动规则vo
     */
    @Data
    @ApiModel("MarketPtActivityVO.CheckActivity")
    public static class CheckActivity implements Serializable {
        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String activityDescribe;

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
        private LocalDateTime activityStartTime;


        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime activityEndTime;

        @ApiModelProperty("可报名店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private String typeCode;

        @ApiModelProperty("参加活动的类目")
        private String activityCategory;

        @ApiModelProperty("参加活动的类目名字")
        private List<MarketPtActivityVO.CategoryJoinSearchVO>  activityCategoryName;

        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer goodsMax;

        @ApiModelProperty("商品折扣范围")
        private String discountRange;
    }
    @Data
    @ApiModel("MarketPtActivityVO.CategoryJoinSearchVO")
    public static class CategoryJoinSearchVO implements Serializable{
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;
    }

    /**
     * 商家活动报名——》操作——》查看详情
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("MarketPtActivityVO.MerchantViewDetails")
    public static class MerchantViewDetails implements Serializable {
        @ApiModelProperty("活动信息")
        private MarketPtActivityVO.CheckActivity activityInfo;
        @ApiModelProperty("参加活动的商品")
        private List<MarketPtActivityVO.ActivityGoods> activityGoodsList;


    }

    /**
     * 商家活动报名——》操作——》查看详情-->参加活动的商品
     */
    @Data
    @Accessors(chain = true)
    @ApiModel("MarketPtActivityVO.ActivityGoods")
    public static class ActivityGoods implements Serializable {
        @ApiModelProperty("活动商品参加记录id")
        private String id;

        @ApiModelProperty("商品名字")
        private String name;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("原价")
        private BigDecimal salePrice;

        @ApiModelProperty("促销价")
        private BigDecimal activitySalePrice;

        @ApiModelProperty("Sku信息")
        private List<MarketPtActivityVO.ActivityGoodsSku> skuInfo;
    }
    /**
     * 商家活动报名——》操作——》查看详情-->参加活动的商品-->查看sku
     * */
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("MarketPtActivityVO.ActivityGoodsSku")
    public static class ActivityGoodsSku implements Serializable {
        @ApiModelProperty("活动商品SKU参加记录id")
        private String id;

        @ApiModelProperty("商品名字")
        private String name;

        @ApiModelProperty("规格值组")
        private String specsValue;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("促销价")
        private BigDecimal activitySaleSkuPrice;
    }

}
