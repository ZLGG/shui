package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeDeliveryVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDeliveryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "发货表(ID)",position = 1)
        private String id;


        @ApiModelProperty(value = "订单ID",position = 2)
        private String tradeId;

        @ApiModelProperty(value = "交易编号",position = 3)
        private String tradeCode;

        @ApiModelProperty(value = "订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消",position = 4)
        private Integer tradeState;


        @ApiModelProperty(value = "会员ID",position = 6)
        private String userId;


        @ApiModelProperty(value = "店铺ID",position = 7)
        private String shopId;


        @ApiModelProperty(value = "商家ID",position = 8)
        private String merchantId;


        @ApiModelProperty(value = "物流公司id",position =9 )
        private String logisticsId;


        @ApiModelProperty(value = "物流公司名称",position = 10)
        private String logisticsName;


        @ApiModelProperty(value = "物流公司编号",position = 11)
        private String logisticsCode;


        @ApiModelProperty(value = "物流编号",position = 12)
        private String logisticsNumber;


        @ApiModelProperty(value = "操作员id",position = 13)
        private String operatorId;


        @ApiModelProperty(value = "作员姓名",position = 14)
        private String operatorName;


        @ApiModelProperty(value = "发货时间",position = 15)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime deliveryTime;


        @ApiModelProperty(value = "发货备注",position = 16)
        private String deliveryRemark;

        @ApiModelProperty(value = "会员名字",position = 17)
        private String userName;

    }
    @Data
    @ApiModel("PCMerchTradeDeliveryVO.ListVOExport")
    @Accessors(chain = true)
    public static class ListVOExport implements Serializable{

        @ApiModelProperty(value = "发货表(ID)",position = 1)
        private String id;


        @ApiModelProperty(value = "订单编号",position = 2)
        private String tradeCode;

        @ApiModelProperty(value = "订单状态",position = 4)
        private String  tradeState;


        @ApiModelProperty(value = "会员名字",position = 6)
        private String userName;


        @ApiModelProperty(value = "店铺名字",position = 7)
        private String shopName;



        @ApiModelProperty(value = "物流公司名称",position = 10)
        private String logisticsName;


        @ApiModelProperty(value = "物流编号",position = 12)
        private String logisticsNumber;


        @ApiModelProperty(value = "作员姓名",position = 14)
        private String operatorName;


        @ApiModelProperty(value = "发货时间",position = 15)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime deliveryTime;


        @ApiModelProperty(value = "发货备注",position = 16)
        private String deliveryRemark;


    }

    @Data
    @ApiModel("PCMerchTradeDeliveryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
