package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author tangxu
 * @version 1.0
 * @date 2020/12/10 18:12
 */
public class TradePayOfflineQTO implements Serializable {

    @Data
    @ApiModel("TradePayOfflineQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("转帐金额")
        private BigDecimal payAmount;

        @ApiModelProperty("test")
        private BigDecimal test;

        @ApiModelProperty("转帐金额起")
        private BigDecimal payAmountStart;

        @ApiModelProperty("转帐金额止")
        private BigDecimal payAmountEnd;

        @ApiModelProperty("订单编号")
        private String tradeId;

        @ApiModelProperty("银行账号")
        private String accountNumber;

        @ApiModelProperty("账户名")
        private String accountName;

        @ApiModelProperty("银行名称")
        private String bankName;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("创建时间")
        private LocalDateTime cDate;

        @ApiModelProperty("转帐金额查询类型(0未开启高级筛选1大于2小于3等于4小于等于5大于等于6介于)")
        private Integer payAmountType;

        @ApiModelProperty("创建时间查询类型(0未开启高级筛选1晚于2早于3是4介于)")
        private Integer cDateType;

        @ApiModelProperty("创建时间起")
        private LocalDateTime cDateStart;

        @ApiModelProperty("创建时间止")
        private LocalDateTime cDateEnd;
    }


    @Data
    @ApiModel("TradePayOfflineQTO.IdListQTO")
    public static class IdListQTO extends BaseQTO{

        @ApiModelProperty("线下审核ID列表")
        private List<String> idList;

    }

}
