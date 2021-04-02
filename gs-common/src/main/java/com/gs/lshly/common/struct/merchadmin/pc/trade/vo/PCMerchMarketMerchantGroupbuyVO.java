package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
* @since 2020-12-10
*/
public abstract class PCMerchMarketMerchantGroupbuyVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("团购活动名称")
        private String groupbuyName;


        @ApiModelProperty("描述")
        private String groupbuyDescribe;


        @ApiModelProperty("适用平台[10=pc端 20=wap端 30=app端](用逗号隔开)（CardTerminalTypeEnum）")
        private String terminal;

        @ApiModelProperty("参数与次数")
        private Integer userDoNumber;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;


        @ApiModelProperty("平台审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;


        @ApiModelProperty("逻辑提交审核标记")
        private Boolean isCommit;


        @ApiModelProperty("逻辑取消标记")
        private Boolean isCancel;


        @ApiModelProperty("拒审原因")
        private String revokeWhy;
    }
    /**
     * 平台--》满赠列表--》查看
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.PlatformCutView")
    public static class PlatformCutView extends ListVO {
        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("组合价")
        private BigDecimal totalPrice;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("商品信息")
        private List<PCMerchMarketMerchantGroupbuyVO.GoodsInfo> goodsInfos;
        @ApiModelProperty("所属商家")
        private List<PCMerchMarketMerchantCardVO.PlatformView.CheckVO> checkInfo;


        @Data
        @ApiModel("PCMerchMarketMerchantGroupbuyVO.PlatformCutView.CheckVO")
        @AllArgsConstructor
        @NoArgsConstructor
        @Accessors(chain = true)
        public static class  CheckVO  implements Serializable{
            @ApiModelProperty("审核时间")
            @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private LocalDateTime checkDate;

            @ApiModelProperty("审核状态")
            private Integer checkState;

            @ApiModelProperty("原因")
            private String remark;
        }
    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.GoodsInfo")
    public static class GoodsInfo implements Serializable {
        @ApiModelProperty("SKUid")
        private String id;
        @ApiModelProperty("商品信息+规格名")
        private String goodsName;
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
        @ApiModelProperty("商品促销价")
        private BigDecimal groupbuyPrice;

    }
    /**
     * 平台端列表
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.PlatformView")
    public static class PlatformView implements Serializable {
        //PlatformCardStatusEnum
        @ApiModelProperty("促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]")
        private Integer promotionStatus;

        @ApiModelProperty("所属商家")
        private String shopName;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("团购活动名称")
        private String groupbuyName;

        @ApiModelProperty("适用平台[10=pc端 20=wap端 30=app端](用逗号隔开)（CardTerminalTypeEnum）")
        private String terminal;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    /**
     * 商家端列表VO
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.ViewVO")
    public static class ViewVO implements Serializable {
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("团购活动名称")
        private String groupbuyName;

        @ApiModelProperty("满减规则(JSON)")
        private String groupbuyRule="团购";

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("平台审核状态枚举类型[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("使用平台[10=pc端 20=wap端 30=app端]")
        private String terminal;

        //MarketPtCutStatusEnum
        //10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]
        @ApiModelProperty("状态枚举类[10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]")
        private Integer condition;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;
    }
    /**
     * 商家端查看
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.View")
    public static class View implements Serializable {
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("团购活动名称")
        private String groupbuyName;

        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private String terminal;

        @ApiModelProperty("参数与次数")
        private Integer userDoNumber;

        @ApiModelProperty("团购描述")
        private String groupbuyDescribe;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("已选择适用商品")
        private List<PCMerchMarketMerchantGroupbuyVO.ViewGoodsGift> goodsList;


    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.ViewGoodsGift")
    public static class ViewGoodsGift implements Serializable {
        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("图片URL")
        private String imageUrl;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("团购价价格")
        private BigDecimal groupbuyPrice;

        @ApiModelProperty("sku信息")
        private List<PCMerchMarketMerchantGroupbuyVO.ActivityGoodsSku> skuInfo;
    }
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("PCMerchMarketMerchantGroupbuyVO.ActivityGoodsSku")
    public static class ActivityGoodsSku implements Serializable {

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
