package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class TradeMarginDetailVO implements Serializable {

    @Data
    @ApiModel("TradeMarginDetailVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;


        @ApiModelProperty(value = "保证金ID",position = 2)
        private String marginId;


        @ApiModelProperty(value = "商家ID",position = 3)
        private String shopId;


        @ApiModelProperty(value = "店铺名称",position = 4)
        private String shopName;


        @ApiModelProperty(value = "用户ID",position = 5)
        private String userId;


        @ApiModelProperty(value = "用户姓名",position = 6)
        private String userName;


        @ApiModelProperty(value = "交易金额",position = 7)
        private BigDecimal payAmount;


        @ApiModelProperty(value = "银行交易流水号",position = 8)
        private String bankSerialNum;


        @ApiModelProperty(value = "关联订单编号",position = 9)
        private String tradeCode;


        @ApiModelProperty(value = "处罚理由",position = 10)
        private String penaltyReason;


        @ApiModelProperty(value = "违规描述",position = 11)
        private String illegalDescription;


        @ApiModelProperty(value = "交易类型(10充值 20扣款 30额度调整)",position = 12)
        private Integer payType;


        @ApiModelProperty(value = "备注",position = 13)
        private String comment;


        @ApiModelProperty(value = "日志创建时间",position = 14)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("TradeMarginDetailVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
