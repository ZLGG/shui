package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 上午11:52
 */
@Data
@Accessors(chain = true)
public class QueryAccountBalanceParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "账户类型:E-电子账户 V-虚账户")
    private String accountType;

    @ApiModelProperty(value = "虚拟户类型:空/100-默认虚账户 101-红包虚账户")
    private String businessType;

    @ApiModelProperty(value = "查询类型:空/0-虚账户+电子账户余额 1-只查虚账户余额")
    private String queryType;

    @ApiModelProperty(value = "二二三类户卡号：6225687352XXXXXX")
    private String subAccNo;
}
