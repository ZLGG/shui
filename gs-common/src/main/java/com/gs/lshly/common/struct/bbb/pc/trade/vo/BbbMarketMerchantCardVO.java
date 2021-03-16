package com.gs.lshly.common.struct.bbb.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public abstract class BbbMarketMerchantCardVO implements Serializable {

    @Data
    @ApiModel("BbbMarketMerchantCardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("商家优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String cardDescribe;

        //MerchantCardOperationEnum
        @ApiModelProperty("操作枚举类型[10=未审核 20=可领取 30=待生效 40=已取消 50=结束领取 60=生效中 70=待领取 80=已失效]")
        private String operation ;


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


        @ApiModelProperty("已领取总数")
        private Integer receivedTotal;


        @ApiModelProperty("已使用总数")
        private Integer usedTotal;


        @ApiModelProperty("可领时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getStartTime;


        @ApiModelProperty("可领时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getEndTime;


        @ApiModelProperty("有效时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validStartTime;


        @ApiModelProperty("有效时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validEndTime;


    }


}
