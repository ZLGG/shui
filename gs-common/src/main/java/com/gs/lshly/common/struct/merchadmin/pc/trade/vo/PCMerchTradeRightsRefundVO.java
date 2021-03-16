package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-17
*/
public abstract class PCMerchTradeRightsRefundVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsRefundVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后退款表ID")
        private String id;


        @ApiModelProperty("售后表ID")
        private String rightsId;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


        @ApiModelProperty("退款结果(10:成功,20:失败)")
        private Integer state;


        @ApiModelProperty("退款结果信息")
        private String resultInfo;




    }

    @Data
    @ApiModel("PCMerchTradeRightsRefundVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
