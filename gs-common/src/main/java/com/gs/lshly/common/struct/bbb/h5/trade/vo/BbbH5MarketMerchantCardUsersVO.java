package com.gs.lshly.common.struct.bbb.h5.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author zdf
* @since 2021-01-08
*/
public abstract class BbbH5MarketMerchantCardUsersVO implements Serializable {

    @Data
    @ApiModel("BbbH5MarketMerchantCardUsersVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家优惠卷ID")
        private String cardId;


        @ApiModelProperty("优惠卷名称")
        private String cardName;


        @ApiModelProperty("描述")
        private String userDescribe;


        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private Integer terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("满多少")
        private BigDecimal toPrice;


        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;


        @ApiModelProperty("有效时间开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validStartTime;


        @ApiModelProperty("有效时间结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validEndTime;


        @ApiModelProperty("使用状态[10=未使用 20=已使用]")
        private Integer state;




    }

    @Data
    @ApiModel("BbbH5MarketMerchantCardUsersVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @ApiModel("BbbH5MarketMerchantCardUsersVO.PageData")
    public static class PageData implements Serializable {

        @ApiModelProperty("优惠卷id")
        private String cardId;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        @ApiModelProperty("优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String cardDescribe;

        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("有效时间开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validStartTime;

        @ApiModelProperty("有效时间结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validEndTime;

    }
}
