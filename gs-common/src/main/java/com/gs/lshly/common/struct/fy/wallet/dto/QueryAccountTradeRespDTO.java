package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午5:31
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询交易响应信息")
public class QueryAccountTradeRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "当前页")
    private String currentPage;

    @ApiModelProperty(value = "总记录数")
    private String totalCount;

    @ApiModelProperty(value = "总页数")
    private String totalPage;

    @ApiModelProperty(value = "交易列表")
    private List<AccountTradeInfoDTO> accountTradeInfo;

    @Data
    @ApiModel(value = "QueryAccountTradeRespDTO.AccountTradeInfoDTO")
    @Accessors(chain = true)
    public static class AccountTradeInfoDTO implements Serializable {

        @ApiModelProperty(value = "原交易流水号")
        private String merchantSsn;

        @ApiModelProperty(value = "支付类型")
        private String payType;

        @ApiModelProperty(value = "交易类型")
        private String tradeType;

        @ApiModelProperty(value = "交易状态: 90:成功 99：失败 33：异常0:默认（无意义）")
        private String tradeStatus;

        @ApiModelProperty(value = "原交易响应码")
        private String resCode;

        @ApiModelProperty(value = "原交易响应描述")
        private String resMsg;

        @ApiModelProperty(value = "交易日期:yyyyMMdd")
        private String transDate;

        @ApiModelProperty(value = "交易金额:单位元")
        private String transferAmt;

        @ApiModelProperty(value = "手续费:单位元")
        private String freeAmt;

        @ApiModelProperty(value = "付款账户")
        private String payAccountNo;

        @ApiModelProperty(value = "付款账户名称")
        private String payAccountName;

        @ApiModelProperty(value = "收款账户")
        private String recAccountNo;

        @ApiModelProperty(value = "收款账户名称")
        private String recAccountName;

        @ApiModelProperty(value = "交易退款标记 0-未退款 1-已经退款 2-已经撤销")
        private String refundFlag;

        @ApiModelProperty(value = "记账日期")
        private String recordDate;

    }


}
