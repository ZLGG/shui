package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-08
*/
public abstract class PCMerchMarketMerchantCutVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantCutVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;



        @ApiModelProperty("满减活动名称")
        private String cutName;


        @ApiModelProperty("描述")
        private String cut_describe;


        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("会员可参与次数")
        private Integer userDoNumber;


        @ApiModelProperty("满减规则(JSON)")
        private String cutRule;


        @ApiModelProperty("是否上不封顶[10=是 20=否]")
        private Integer isNotMax;


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

    @Data
    @ApiModel("PCMerchMarketMerchantCutVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    /**
     * 商家满减管理列表VO
     *
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantCutVO.ViewVO")
    public static class ViewVO implements Serializable {
        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("满减活动名称")
        private String cutName;

        @ApiModelProperty("满减规则(JSON)")
        private String cutRule;

        @ApiModelProperty("描述")
        private String cutDescribe;


        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private String terminal;

        @ApiModelProperty("提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("平台审核状态枚举类型[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;
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

    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantCutVO.View")
    public static class View implements Serializable {
        @ApiModelProperty("满减活动名称")
        private String cutName;


        @ApiModelProperty("描述")
        private String cutDescribe;


        @ApiModelProperty("满减规则(JSON)(是否封顶)")
        private String cutRule;
        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("会员可参与次数")
        private Integer userDoNumber;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("适用商品")
        private List<PCMerchMarketMerchantCutVO.ViewGoods> goodsList;
    }
        @Data
        @Accessors(chain = true)
        @ApiModel("PCMerchMarketMerchantCutVO.ViewGoods")
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
    @ApiModel("PCMerchMarketMerchantCutVO.PlatformView")
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

        @ApiModelProperty("提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("平台审核状态枚举类型[10=待审 20=通过 30=拒审]")

        private Integer state;
        @ApiModelProperty("满减活动名称")
        private String cutName;

        @ApiModelProperty("满减规则(JSON)")
        private String cutRule;

        @ApiModelProperty("描述")
        private String cutDescribe;

        @ApiModelProperty("适用平台[10=pc端 20=wap端 30=app端]")
        private String terminal;
        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;
    }
    /**
     * 平台--》满减列表--》查看
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantCutVO.PlatformCutView")
    public static class PlatformCutView extends ListVO {
        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("创建时间")
        private LocalDateTime cdate;

        @ApiModelProperty("商品信息")
        private List<PCMerchMarketMerchantCutVO.GoodsInfo> goodsInfos;
    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantCutVO.GoodsInfo")
    public static class GoodsInfo implements Serializable {
        @ApiModelProperty("SKUid")
        private String id;
        @ApiModelProperty("商品信息+规格名")
        private String goodsName;
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

    }
}
