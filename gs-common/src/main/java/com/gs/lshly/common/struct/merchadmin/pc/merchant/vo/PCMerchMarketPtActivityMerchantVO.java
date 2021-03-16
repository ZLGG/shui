package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
* @since 2020-12-01
*/
public abstract class PCMerchMarketPtActivityMerchantVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动ID")
        private String activityId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("状态[10=审核 20=未审核 30=审核驳回]")
        private String  state;

        @ApiModelProperty("审核驳回原因")
        private String reasonsForRejection;

        @ApiModelProperty("报名开始时间")
        private LocalDateTime signStartTime;


        @ApiModelProperty("报名结束时间")
        private LocalDateTime signEndTime;


        @ApiModelProperty("活动上线时间")
        private LocalDateTime onlineStartTime;


        @ApiModelProperty("开售开始时间")
        private LocalDateTime activityStartTime;


        @ApiModelProperty("开售结束时间")
        private LocalDateTime activityEndTime;


        @ApiModelProperty("会员限购数量上限")
        private Integer userBuyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer shopGoodsMax;


        @ApiModelProperty("活动封面图")
        private String coverImage;


        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;


        @ApiModelProperty("是否短信提醒[10=是 20=否]")
        private Integer smsIsTell;




    }

    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantVO.MerchantActivity")
    public static class MyMerchantActivity implements Serializable {
        @ApiModelProperty("活动记录id")
        private String id;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("活动类型")
        private String label;

        @ApiModelProperty("报名时间")
        private String signTime;


        @ApiModelProperty("活动时间时间")
        private String activityTime;

        @ApiModelProperty("状态[10=审核 20=未审核]")
        private String state;

    }

    /**
     * 平台活动审核列表
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtActivityMerchantVO.platformCheck")
    public static class platformCheck implements Serializable {
        @ApiModelProperty("商家活动记录ID")
        private String id;


        @ApiModelProperty("操作[10=审核通过 20=审核 30=审核驳回 40=无法审核](平台端无视)")
        private String operation;


        @ApiModelProperty("店铺名（店铺类型）")
        private String shopName;

        @ApiModelProperty("活动名")
        private String activityName;


        @ApiModelProperty("状态[10=审核通过 20=待审核 30=审核被拒绝]")
        private String check;

        @ApiModelProperty("有效状态[10=是 20=否]")
        private String state;


        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udata;

        /**
         * 报名开始时间
         */
        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signStartTime;

        /**
         * 报名结束时间
         */
        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signEndTime;
    }
    /**
     * 平台活动审核列表
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtActivityMerchantVO.platformCheckView")
    public static class platformCheckView implements Serializable {
        @ApiModelProperty("活动规则")
        private MarketPtActivityVO.CheckActivity activity;


        @ApiModelProperty("店铺信息")
        private PCMerchMarketPtActivityMerchantVO.platformCheck platformInfo;


        @ApiModelProperty("商品信息")
        private List<PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo> goodsInfoList;

    }
    /**
     * 商品信息
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo")
    public static class platformCheckGoodsInfo implements Serializable {
        @ApiModelProperty("商品ID")
        private String goodsId;
        @ApiModelProperty("商品编号")
        private String goodsNo;


        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品活动价")
        private BigDecimal activityPrice;

        @ApiModelProperty("Sku活动价枚举类型[10=SKU促销价 20=此商品无sku值]")
        private PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo skuActivityPriceInfos;
        //ActivitySignEnum
        @ApiModelProperty("审核状态枚举类型[10=已审核 20=待审核]")
        private Integer checkState;
    }
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo")
    public static class skuActivityPriceInfo implements Serializable {
        @ApiModelProperty("Sku活动价枚举类型[10=SKU促销价 20=此商品无sku值]")
        private Integer skuActivityPrice;
        @ApiModelProperty("商品SKUid")
        private List<String> id;

    }

}
