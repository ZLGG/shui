package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
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
* @author zst
* @since 2020-12-10
*/
public abstract class TradeMarginDetailQTO implements Serializable {

    @Data
    @ApiModel("TradeMarginDetailQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("筛选(10大于 20小于 30等于 40大于等于 50小于等于 60介于)")
        private Integer marginDetailScreen;

        @ApiModelProperty("交易金额")
        private BigDecimal payPmount;

        @ApiModelProperty("交易类型[10充值 20扣款 30额度调整]")
        private Integer payType;

        @ApiModelProperty("备注")
        private String comment;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员姓名")
        private String userName;

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("交易金额大于")
        private BigDecimal payGe;

        @ApiModelProperty("交易金额小于")
        private BigDecimal payLe;
    }


    @Data
    @ApiModel("TradeMarginDetailQTO.typeQTO")
    @Accessors(chain = true)
    public static class typeQTO extends BaseQTO {

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("交易类型")
        private Integer payType;

    }


    @Data
    @ApiModel("TradeMarginDetailQTO.viewQTO")
    @Accessors(chain = true)
    public static class viewQTO extends BaseDTO {

    }

    @Data
    @ApiModel("TradeMarginDetailQTO.IdListQTO")
    public static class IdListQTO extends BaseQTO{

        @ApiModelProperty("保证金明细ID列表")
        private List<String> idList;

    }
}
