package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午6:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "验证短信验证码请求信息")
public class ReplyMsgCodeParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "原消费订单流水号", required = true)
    @NotBlank(message = "原消费订单流水号不能为空")
    private String orgSenderSsn;

    @ApiModelProperty(value = "验证码:6位",required = true)
    @Pattern(regexp = "^[000000-999999]*$", message = "验证码错误")
    private String smsCode;
}
