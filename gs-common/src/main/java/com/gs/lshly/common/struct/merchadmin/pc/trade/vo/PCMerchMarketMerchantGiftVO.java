package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
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
* @since 2020-12-09
*/
public abstract class PCMerchMarketMerchantGiftVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGiftVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("满赠活动名称")
        private String giftName;


        @ApiModelProperty("描述")
        private String giftDescribe;


        @ApiModelProperty("适用平台[10=pc端 20=wap端 30=app端]（CardTerminalTypeEnum）")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("会员可参与次数")
        private Integer userDoNumber;


        @ApiModelProperty("满减规则(JSON)")
        private String scountRule;


        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("平台审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("退回赠品[10=退回 20=不退回]")
        private Integer returnGift;

        @ApiModelProperty("逻辑提交审核标记")
        private Boolean isCommit;


        @ApiModelProperty("逻辑取消标记")
        private Boolean isCancel;


        @ApiModelProperty("拒审原因")
        private String revokeWhy;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantGiftVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    /**
     * 商家端列表VO
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantGiftVO.ViewVO")
    public static class ViewVO implements Serializable {
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("满赠活动名称")
        private String giftName;

        @ApiModelProperty("满减规则(JSON)")
        private String scountRule;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("平台审核状态枚举类型[10=待审 20=通过 30=拒审]")
        private Integer state;

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
    @ApiModel("PCMerchMarketMerchantGiftVO.View")
    public static class View implements Serializable {
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("满赠活动名称")
        private String giftName;

        @ApiModelProperty("满减规则(JSON)")
        private String scountRule;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("赠品列表")
        private List<PCMerchMarketMerchantGiftVO.ViewGoodsGift> giftView;

        @ApiModelProperty("适用商品")
        private List<PCMerchMarketMerchantGiftVO.ViewGoods> goodsList;


    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.ViewGoodsGift")
    public static class ViewGoodsGift implements Serializable {

        @ApiModelProperty("图片URL")
        private String imageUrl;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("规格")
        private String specsValue;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;
    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.ViewGoods")
    public static class ViewGoods implements Serializable {
        @ApiModelProperty("商品ID")
        private String goodsId;
        @ApiModelProperty("图片URL")
        private String imageUrl;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("查看SKU")
        private List<PCMerchSkuGoodInfoVO.ListVO> viewSKU;
    }

    /**
     * 平台端列表
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.PlatformView")
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

        @ApiModelProperty("满赠活动名称")
        private String giftName;

        @ApiModelProperty("满减规则(JSON)")
        private String scountRule;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;
        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
    }
    /**
     * 平台--》满赠列表--》查看
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.PlatformCutView")
    public static class PlatformCutView extends ListVO {
        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("商品信息")
        private List<PCMerchMarketMerchantGiftVO.GoodsInfo> goodsInfos;

        @ApiModelProperty("赠品列表")
        private List<PCMerchMarketMerchantGiftVO.GoodsGiveInfo> giftView;

    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.GoodsInfo")
    public static class GoodsInfo implements Serializable {
        @ApiModelProperty("SKUid")
        private String id;
        @ApiModelProperty("商品信息+规格名")
        private String goodsName;
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantGiftVO.GoodsGiveInfo")
    public static class GoodsGiveInfo implements Serializable {
        @ApiModelProperty("SKUid")
        private String id;
        @ApiModelProperty("商品信息+规格名")
        private String goodsGiveName;
        @ApiModelProperty("赠送数量")
        private Integer total;

    }
}
