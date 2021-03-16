package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:35
 */
@Data
@Accessors(chain = true)
public class QueryAccountTradeListRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "二三类户卡号:开户成功时返回")
    private String electronicAccountNo;

    @ApiModelProperty(value = "总页数")
    private String turnPageTotalPage;

    @ApiModelProperty(value = "末页标识")
    private String lastPageFlag;

    @ApiModelProperty(value = "总记录数:首次查询送0，翻页时 将上次核心返回的结果 再次送入核心即")
    private String totalNum;

    @ApiModelProperty(value = "当前页:首次查询时送0，翻页 查询时第1页，页码是 1，第2页，页码是 2，以此类推")
    private String currPage;

    @ApiModelProperty(value = "集合长度")
    private String tradeList_NUM;

    @ApiModelProperty(value = "记账日期")
    private String recordDate;

    @ApiModelProperty(value = "交易渠道")
    private String tradeChannel;

    @ApiModelProperty(value = "交易类型:C-入账， D-出账")
    private String tradeType;

    @ApiModelProperty(value = "币种")
    private String currencyType;

    @ApiModelProperty(value = "交易流水")
    private String transFlowNo;

    @ApiModelProperty(value = "内部序列号")
    private String innerSerno;

    @ApiModelProperty(value = "账户余额:开放平台需转换，核心 是0000000012的形式 ，负数也要转")
    private String eAccAmt;

    @ApiModelProperty(value = "交易金额:开放平台需转换，核心 是0000000012的形式 ，负数也要转")
    private String transferAmt;

    @ApiModelProperty(value = "对方账号")
    private String recAccountNo;

    @ApiModelProperty(value = "对方姓名")
    private String recAccountName;

    @ApiModelProperty(value = "摘要码中文名称")
    private String handAmount;

    @ApiModelProperty(value = "摘要码")
    private String handAmountCode;

    @ApiModelProperty(value = "交易时间")
    private String transTime;

    @ApiModelProperty(value = "更正标志:N:正常的交易。 C:交易发生冲正，原交 易状态将被置为C状态 （交易金额、借贷方向 均不变） R:交易冲正成功，需要 写入一条冲正的历史 ，这条历史的状态就是 R（不跨年的情况下 ，交易借贷方向与原交 易一直，交易金额前面 加负号；跨年则交易金 额一样，借贷相反）")
    private String changeFlag;

    @ApiModelProperty(value = "冲账标志:N:正常的交易。 C:交易发生冲正，原交 易状态将被置为C状态 （交易金额、借贷方向 均不变） R:交易冲正成功，需要 写入一条冲正的历史 ，这条历史的状态就是 R（不跨年的情况下 ，交易借贷方向与原交 易一直，交易金额前面 加负号；跨年则交易金 额一样，借贷相反）")
    private String flag;
}
