package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-09
*/
public abstract class PCMerchMarketMerchantGiftDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGiftDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;

        @ApiModelProperty(value = "店铺ID",hidden = true)
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
    @ApiModel("PCMerchMarketMerchantGiftDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
    @Data
    @ApiModel("PCMerchMarketMerchantGiftDTO.AddDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddDTO extends ETO {
        @ApiModelProperty(value = "商品信息")
        private List<PCMerchMarketMerchantGiftGoodsDTO.ETO> goodsList;
        @ApiModelProperty(value = "赠品信息")
        private List<PCMerchMarketMerchantGiftGoodsGiveDTO.ETO> goodsGiveList;
    }


    @Data
    @ApiModel("PCMerchMarketMerchantGiftDTO.Check")
    @AllArgsConstructor
    public static class Check extends BaseDTO {

        @ApiModelProperty(value = "商家满增id")
        private String id;
        //ActivitySignEnum
        @ApiModelProperty(value = "审核方式枚举类型[10=审核通过 30=审核驳回]")
        private Integer pattern;
        @ApiModelProperty(value = "驳回原因")
        private String revokeWhy ;
    }
}
