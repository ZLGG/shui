package com.gs.lshly.common.struct.bbb.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zhaoyan
* @since 2020-10-15
*/
public abstract class BbbOrderVO implements Serializable {

    @Data
    @ApiModel("BBB.OrderVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("订单号(ID)")
        private String id;


        @ApiModelProperty("订单状态")
        private Integer orderState;


        @ApiModelProperty("创建时间")
        private LocalDateTime orderCreateTime;


        @ApiModelProperty("支付时间")
        private LocalDateTime payTime;


        @ApiModelProperty("支付类型")
        private String payType;


        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;


        @ApiModelProperty("收货地址ID")
        private Integer recvAddresId;


        @ApiModelProperty("收货人")
        private String recvPersonName;


        @ApiModelProperty("收货人电话")
        private String recvPhone;


        @ApiModelProperty("收货时间")
        private LocalDateTime recvTime;


        @ApiModelProperty("取消订单原因")
        private String cancelWhy;


        @ApiModelProperty("发货物流号")
        private String logisticsCode;


        @ApiModelProperty("发货时间")
        private String sendTime;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("BBB.OrderVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("BbbOrderVO.InnerCompareTo")
    @AllArgsConstructor
    public static class InnerCompareTo implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;


        @ApiModelProperty("评分")
        private Double grade;
    }
    @Data
    @ApiModel("BbbOrderVO.InnerCompareToCount")
    public static class InnerCompareToCount implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;


        @ApiModelProperty("销量")
        private Integer count;
    }
}
