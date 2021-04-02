package com.gs.lshly.common.struct.bbc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
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
* @since 2020-12-17
*/
public abstract class BbcMarketActivityVO implements Serializable {

    @Data
    @ApiModel("BbcMarketActivityVO.ListYiVO")
    @Accessors(chain = true)
    public static class ListYiVO implements Serializable {
        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名字（名字+标题）")
        private String goodsName;

        @ApiModelProperty("图片")
        private String goodsImg;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("原价")
        private BigDecimal oldPrice;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.cutVO")
    @Accessors(chain = true)
    public static class cutVO extends ListYiVO {
        @ApiModelProperty("满减活动id")
        private String cutId;

        @ApiModelProperty("满减活动名字")
        private String cutName;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("活动规则")
        private String cutRule;

        @ApiModelProperty("是否上不封顶[10=是 20=否]")
        private Integer isNotMax;
    }
    @Data
    @ApiModel("BbcMarketActivityVO.cutRuleVO")
    @Accessors(chain = true)
    public static class cutRuleVO implements Serializable {
        @ApiModelProperty("满多少件")
        private Integer toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.discountVO")
    @Accessors(chain = true)
    public static class discountVO extends ListYiVO {
        @ApiModelProperty("满折活动id")
        private String id;

        @ApiModelProperty("满折活动名称")
        private String scountName;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("活动规则")
        private String scountRule;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.groupbuyVO")
    @Accessors(chain = true)
    public static class groupbuyVO extends ListYiVO {
        @ApiModelProperty("团购活动id")
        private String id;

        @ApiModelProperty("团购活动名称")
        private String groupbuyName;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("活动价")
        private BigDecimal groupbuyPrice;


    }
    @Data
    @ApiModel("BbcMarketActivityVO.activityListPageVO")
    @Accessors(chain = true)
    public static class activityListPageVO implements Serializable{
        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("宣传图")
        private String coverImage;

        @ApiModelProperty("优惠折扣范围")
        private String discountRange;

        @ApiModelProperty("活动开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityStartTime;

        @ApiModelProperty("活动结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityEndTime;

    }

    @Data
    @ApiModel("BbcMarketActivityVO.activityVO")
    @Accessors(chain = true)
    public static class activityVO implements Serializable{
        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("宣传图")
        private String coverImage;

        @ApiModelProperty("优惠折扣范围")
        private String discountRange;

        @ApiModelProperty("活动开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityStartTime;

        @ApiModelProperty("活动结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityEndTime;

        @ApiModelProperty("活动商品")
        private PageData<activityGoodsVO> goodsVOList;

    }

    @Data
    @ApiModel("BbcMarketActivityVO.activityGoodsVO")
    @Accessors(chain = true)
    public static class activityGoodsVO extends ListYiVO{
        @ApiModelProperty("活动价")
        private BigDecimal activityPrice;
    }

    @Data
    @ApiModel("BbcMarketActivityVO.giftVO")
    @Accessors(chain = true)
    public static class giftVO extends ListYiVO {
        @ApiModelProperty("满增活动id")
        private String id;

        @ApiModelProperty("满增活动名称")
        private String giftName;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("活动规则")
        private String scountRule;

        @ApiModelProperty("赠品")
        private List<giftGiveVO> giftGiveVOList;
        @Data
        @ApiModel("BbcMarketActivityVO.giftVO.giftGiveVO")
        @Accessors(chain = true)
        public static class giftGiveVO implements Serializable{
            @ApiModelProperty("赠品名字")
            private String giftGiveName;

            @ApiModelProperty("赠品商品SKUID")
            private String giftGiveSkuId;

            @ApiModelProperty("赠品商品ID")
            private String giftGoodsId;
        }


    }
    @Data
    @ApiModel("BbcMarketActivityVO.merchantCard")
    @Accessors(chain = true)
    public static class merchantCard implements Serializable{
        @ApiModelProperty("优惠卷id")
        private String id;

        @ApiModelProperty("优惠卷名字")
        private String name;


        @ApiModelProperty("面额")
        private BigDecimal faceValue;

        @ApiModelProperty("规则")
        private String rule;

        @ApiModelProperty("是否可领取[10=可以 20=不可以]")
        private Integer isReceive;
    }
    @Data
    @ApiModel("PCBbbMarketActivityVO.jurisdiction")
    @Accessors(chain = true)
    public static class jurisdiction implements Serializable{
        @ApiModelProperty("PC端")
        private List<state> pcActivity;
        @ApiModelProperty("H5端")
        private List<state> h5Activity;

        @Data
        @ApiModel("PCBbbMarketActivityVO.jurisdiction.state")
        @Accessors(chain = true)
        @AllArgsConstructor
        @NoArgsConstructor
        public static class state implements Serializable{
            @ApiModelProperty("活动类型[10=团购 20=折扣 30=满减 40=满赠]")
            private Integer activity;

            @ApiModelProperty("状态[10=隐藏 20=开放]")
            private Integer activityState;
        }
    }
    @Data
    @ApiModel("BbcMarketActivityVO.merchantCardSuccess")
    @Accessors(chain = true)
    public static class merchantCardSuccess implements Serializable{
        @ApiModelProperty("优惠卷id")
        private String id;

        @ApiModelProperty("优惠卷名字")
        private String cardName ;

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("规则")
        private String rule;

        @ApiModelProperty("活动有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty("使用平台")
        private String terminal;
    }
    @Data
    @ApiModel("BbcMarketActivityVO.ListCardVO")
    @Accessors(chain = true)
    public static class ListCardVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;
        @ApiModelProperty("商家优惠卷名称")
        private String cardName;


        @ApiModelProperty("描述")
        private String cardDescribe;
        @ApiModelProperty("适用平台枚举类型[10=pc端 20=wap端 30=app端]")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("满多少")
        private BigDecimal toPrice;


        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;


        @ApiModelProperty("优惠卷发行数量")
        private Integer quantity;


        @ApiModelProperty("每人可领取数量")
        private Integer userGetMax;
        @ApiModelProperty("可领时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getStartTime;


        @ApiModelProperty("可领时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getEndTime;

        @ApiModelProperty("状态")
        private String status;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("平台活动")
        private List<PlataformActivity> plataformActivity;
        @ApiModelProperty("商家满减活动")
        private List<CutActivity> cutActivity;
        @ApiModelProperty("商家满折活动")
        private List<ScountActivity> scountActivity;
        @ApiModelProperty("商家满增活动")
        private List<GiftActivity> giftActivity;
        @ApiModelProperty("商家团购活动")
        private List<GroupbuyActivity> groupbuyActivity;@ApiModelProperty("商家优惠卷")
        private List<BbcMarketActivityVO.merchantCard> cardActivity;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.PlataformActivity")
    @Accessors(chain = true)
    public static class PlataformActivity implements Serializable{
        @ApiModelProperty("平台活动ID")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("折扣范围")
        private String discountRange;

        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime activityStartTime;


        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime activityEndTime;


        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;

        @ApiModelProperty("活动封面图")
        private String coverImage;

        @ApiModelProperty("状态")
        private String status;

    }
    @Data
    @ApiModel("BbbMarketActivityVO.CutActivity")
    @Accessors(chain = true)
    public static class CutActivity implements Serializable{
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("满减活动名称")
        private String cutName;


        @ApiModelProperty("描述")
        private String cut_describe;
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

        @ApiModelProperty("状态")
        private String status;

    }
    @Data
    @ApiModel("BbcMarketActivityVO.ScountActivity")
    @Accessors(chain = true)
    public static class ScountActivity implements Serializable{
        @ApiModelProperty("id")
        private String id;
        @ApiModelProperty("满折活动名称")
        private String scountName;


        @ApiModelProperty("描述")
        private String scountDescribe;
        @ApiModelProperty("会员可参与次数")
        private Integer userDoNumber;


        @ApiModelProperty("满减规则(JSON)")
        private String scountRule;


        @ApiModelProperty("活动有效时间段-开始")
        private LocalDateTime validStartTime;


        @ApiModelProperty("活动有效时间段-结束")
        private LocalDateTime validEndTime;
        @ApiModelProperty("状态")
        private String status;


    }
    @Data
    @ApiModel("BbcMarketActivityVO.GiftActivity")
    @Accessors(chain = true)
    public static class GiftActivity implements Serializable{
        @ApiModelProperty("id")
        private String id;
        @ApiModelProperty("满赠活动名称")
        private String giftName;


        @ApiModelProperty("描述")
        private String giftDescribe;
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
        @ApiModelProperty("退回赠品[10=退回 20=不退回]")
        private Integer returnGift;
        @ApiModelProperty("状态")
        private String status;
    }
    @Data
    @ApiModel("BbcMarketActivityVO.GroupbuyActivity")
    @Accessors(chain = true)
    public static class GroupbuyActivity implements Serializable{
        @ApiModelProperty("id")
        private String id;
        @ApiModelProperty("团购活动名称")
        private String groupbuyName;


        @ApiModelProperty("描述")
        private String groupbuyDescribe;

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
        @ApiModelProperty("状态")
        private String status;
    }


}
