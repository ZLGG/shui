package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author zdf
* @since 2020-12-17
*/
public abstract class PCMerchTradeRightsQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("退货单类型[10=_2C 20=_2B](TradeSourceTypeEnum)")
        private Integer tradeRightType;

        @ApiModelProperty("状态(10:等待审核,20:驳回,30:通过,40:等待买家回寄,50:商家已收货,80:待确认发货,90:已发货,60:等待平台退款,70:退款完成,91:确认收货,95:用户取消,99:已完成)")
        private Integer rightType;

        @ApiModelProperty("退换货ID")
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("退换货类型[10=换货 20=仅退款 30=退货退款 40=取消订单]")
        private Integer tradeRightsType;

        @ApiModelProperty("申请开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @ApiModelProperty("申请结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;



    }
}
