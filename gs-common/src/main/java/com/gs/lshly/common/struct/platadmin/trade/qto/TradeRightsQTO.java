package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-22
*/
public abstract class TradeRightsQTO implements Serializable {

    @Data
    @ApiModel("TradeRightsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("退款单号")
        private String id;

        @ApiModelProperty("退款银行")
        private String refundBankName;

        @ApiModelProperty("退款账户")
        private String refundAccount;

        @ApiModelProperty("退款ren")
        private String refundName;

        @ApiModelProperty("收款银行")
        private String collectBankName;

        @ApiModelProperty("收款账户")
        private String collectAccount;

        @ApiModelProperty("交易主订单号")
        private String tradeId;

        @ApiModelProperty("支付状态[10=成功 20=失败]")
        private Integer state;

        @ApiModelProperty("退款方式[10=线下退款 20=原路返回]")
        private Integer refundType;

        @ApiModelProperty("售后单号")
        private String rightsId;

        @ApiModelProperty("支付开始时间(介于大于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payStartDate;

        @ApiModelProperty("支付开始时间(介于小于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payStartLittleDate;

        @ApiModelProperty("支付开始时间状态[10=晚于 20=早于 30=是 40=介于]")
        private Integer payStartDateState;



    }
    @Data
    @ApiModel("TradeRightsQTO.StateDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StateDTO extends BaseQTO {

        @ApiModelProperty("状态枚举类[10:换货,20:仅退款,30:退货退款]")
        private Integer status;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;

        @ApiModelProperty("订单编号")
        private String tradeId;

        @ApiModelProperty("处理进度(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成) ")
        private Integer state;

        @ApiModelProperty("支付创建时间(介于大于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("支付创建时间(介于小于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdateLittleDate;

        @ApiModelProperty("支付创建时间状态[10=晚于 20=早于 30=是 40=介于]")
        private Integer cdateState;
    }
    @Data
    @ApiModel("TradeRightsQTO.StateDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StateRefundDTO extends BaseQTO {

        @ApiModelProperty("状态枚举类[空为全部 60=退款处理 10=待商家审核 99=已完成 95=已关闭]")
        private Integer status;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("售后编号")
        private String id;
    }

    @Data
    @ApiModel("TradeRightsQTO.NewQTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewQTO extends BaseQTO {

        @ApiModelProperty("支付方式[空为全部 10=支付宝 20=微信扫码 30=积分支付]")
        private Integer payType;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("订单编号")
        private String tradeCode;
    }

}
