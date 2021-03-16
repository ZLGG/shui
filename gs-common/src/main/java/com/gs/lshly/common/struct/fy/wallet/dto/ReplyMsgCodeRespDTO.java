package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午11:23
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "验证短信验证码响应信息")
public class ReplyMsgCodeRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "交易状态:90-成功 99-失败 33异常")
    private String tradeStatus;

    @ApiModelProperty(value = "系统流水号")
    private String serialNo;
}
