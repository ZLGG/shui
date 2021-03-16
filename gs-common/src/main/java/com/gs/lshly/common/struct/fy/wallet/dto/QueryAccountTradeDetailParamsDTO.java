package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:22
 */
@Data
@Accessors(chain = true)
public class QueryAccountTradeDetailParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "内部序列号")
    private String innerSerno;

    @ApiModelProperty(value = "交易流水")
    private String transFlowNo;

    @ApiModelProperty(value = "记账日期:yyyymmdd")
    private String recordDate;

    @ApiModelProperty(value = "交易渠道:yyyymmdd")
    private String tradeChannel;
}
