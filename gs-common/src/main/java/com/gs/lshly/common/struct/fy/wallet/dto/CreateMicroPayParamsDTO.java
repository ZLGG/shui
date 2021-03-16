package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午4:40
 */
@Data
@Accessors(chain = true)
public class CreateMicroPayParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "支付账户号")
    private String payAccount;

}
