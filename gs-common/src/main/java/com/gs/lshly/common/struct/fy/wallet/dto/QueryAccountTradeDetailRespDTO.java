package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:25
 */
@Data
@Accessors(chain = true)
public class QueryAccountTradeDetailRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "操作类型:C-入账，D-出账 为空全部")
    private String transType;

    @ApiModelProperty(value = "币种:C")
    private String currType;

    @ApiModelProperty(value = "交易日期")
    private String transDate;

    @ApiModelProperty(value = "账户余额")
    private String eAccAmt;

    @ApiModelProperty(value = "交易金额")
    private String transferAmt;

    @ApiModelProperty(value = "付款账号姓名")
    private String payAccountName;

    @ApiModelProperty(value = "收款账户:他行收款账户，即绑定 卡号")
    private String recAccountNo;

    @ApiModelProperty(value = "收款方名称")
    private String recAccountName;

    @ApiModelProperty(value = "摘要码中文名称")
    private String handAmount;
}
