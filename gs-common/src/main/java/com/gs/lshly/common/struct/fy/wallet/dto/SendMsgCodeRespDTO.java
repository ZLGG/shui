package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:49
 */
@Data
@Accessors(chain = true)
public class SendMsgCodeRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "短信验证码校验标识:校验短信验证码时需上送的唯一标识，根据此标识去比对上送的短信验证码和实际发送的短信验证码是否一致。")
    private String msgCodeCheckId;
}
