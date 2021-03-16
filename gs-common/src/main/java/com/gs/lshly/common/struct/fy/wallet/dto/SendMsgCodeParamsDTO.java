package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:45
 */
@Data
@Accessors(chain = true)
public class SendMsgCodeParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "卡号:可以是绑定卡、二三类户")
    private String cardNo;

    @ApiModelProperty(value = "身份证号:按18位的身份证规则校验")
    private String certNo;

    @ApiModelProperty(value = "手机号:以1开头的11位数字")
    private String mobileNo;

    @ApiModelProperty(value = "短信业务代码")
    private String SMSID;

    @ApiModelProperty(value = "短信自定义内容:1、开通三类户 2、充值到三类户")
    private String msgContentPart3;
}
