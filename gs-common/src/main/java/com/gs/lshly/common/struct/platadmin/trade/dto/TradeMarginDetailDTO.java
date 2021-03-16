package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-10
*/
public abstract class TradeMarginDetailDTO implements Serializable {

    @Data
    @ApiModel("TradeMarginDetailDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保证金ID")
        private String marginId;

        @ApiModelProperty("商家ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("用户ID")
        private String userId;

        @ApiModelProperty("用户姓名")
        private String userName;

        @ApiModelProperty("交易金额")
        private BigDecimal payAmount;

        @ApiModelProperty("银行交易流水号")
        private String bankSerialNum;

        @ApiModelProperty("关联订单编号")
        private String tradeCode;

        @ApiModelProperty("处罚理由")
        private String penaltyReason;

        @ApiModelProperty("违规描述")
        private String illegalDescription;

        @ApiModelProperty("交易类型(10充值 20扣款 30额度调整)")
        private Integer payType;

        @ApiModelProperty("备注")
        private String comment;
    }

    @Data
    @ApiModel("TradeMarginDetailDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
