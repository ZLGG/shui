package com.gs.lshly.common.struct.bbb.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-23
*/
public abstract class PCBbbTradeComplaintVO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeComplaintVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("投诉id")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("订单skuID")
        private String skuId;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("用户账号")
        private String userName;


        @ApiModelProperty("投诉类型（10=商品问题 20=配送问题 30=支付问题 40=促销活动问题 50=账户问题 60=发票问题 70=系统问题 80=退货/换货问题 90=表扬/投诉工作人员 100=其他）")
        private Integer complaintType;


        @ApiModelProperty("联系方式")
        private String contactWay;


        @ApiModelProperty("问题描述")
        private String problemDesc;


        @ApiModelProperty("投诉时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime complaintTime;


        @ApiModelProperty("处理状态（10=等待处理 20=投诉成功 30 =关闭投诉 40=买家撤销了投诉）")
        private Integer handleState;


        @ApiModelProperty("用户取消投诉理由")
        private String cancenlIdea;


        @ApiModelProperty("处理备注")
        private String platformReply;

    }

    @Data
    @ApiModel("PCBbbTradeComplaintVO.DetailListVO")
    public static class DetailListVO extends ListVO {
        @ApiModelProperty("投诉单号或订单号")
        private String tradeCode;

    }

    @Data
    @ApiModel("PCBbbTradeComplaintVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("投诉单号或订单号")
        private String tradeCode;

        @ApiModelProperty("投诉商品")
        private String goodsName;

        @ApiModelProperty("被投诉方")
        private String shopName;
    }
}
