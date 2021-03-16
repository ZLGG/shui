package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午4:41
 */
@Data
@Accessors(chain = true)
public class CreateMicroPayRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "二维码:Aes加密")
    private String barCode;

    @ApiModelProperty(value = "二维码路径:Aes加密")
    private String barCodeUrl;

    @ApiModelProperty(value = "超时时间:单位秒60")
    private String expireTime;
}
