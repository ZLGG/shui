package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-12-04
*/
public abstract class PCMerchMarketMerchantCardDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantCardDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "优惠卷前缀",hidden = true)
        private String cardPrefix;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String cardDescribe;

        @ApiModelProperty("适用平台枚举类型[10=全平台 20=pc端 30=wap端 40=app端]")
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

        @ApiModelProperty(value = "已领取总数",hidden = true)
        private Integer receivedTotal;

        @ApiModelProperty(value = "已使用总数",hidden = true)
        private Integer usedTotal;

        @ApiModelProperty("可领时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime getStartTime;

        @ApiModelProperty("可领时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime getEndTime;

        @ApiModelProperty("有效时间段-开始")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validStartTime;

        @ApiModelProperty("有效时间段-结束")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime validEndTime;

        @ApiModelProperty(value = "平台审核状态枚举类型[10=待审 20=通过 30=拒审]",hidden = true)
        private Integer state;

        @ApiModelProperty(value = "提交审核标记",hidden = true)
        private Boolean isCommit;

        @ApiModelProperty(value = "取消优惠卷标记",hidden = true)
        private Boolean isCancel;

        @ApiModelProperty(value = "拒审原因",hidden = true)
        private String revokeWhy;

        @ApiModelProperty(value = "商品信息")
        private List<PCMerchMarketMerchantCardGoodsDTO.GoodsInfo> goodsList;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantCardDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
    @Data
    @ApiModel("PCMerchMarketMerchantCardDTO.Check")
    @AllArgsConstructor
    public static class Check extends BaseDTO {

        @ApiModelProperty(value = "商家优惠卷id")
        private String id;
//ActivitySignEnum
        @ApiModelProperty(value = "审核方式枚举类型[10=审核通过 30=审核驳回]")
        private Integer pattern;
        @ApiModelProperty(value = "驳回原因")
        private String revokeWhy ;



    }



}
