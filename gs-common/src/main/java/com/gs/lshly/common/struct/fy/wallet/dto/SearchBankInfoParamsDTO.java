package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:52
 */
@Data
@Accessors(chain = true)
public class SearchBankInfoParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "需要查询银行信息的渠道")
    private String channelCode;
}
