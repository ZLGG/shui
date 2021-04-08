package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author yangxi
 * @create 2021/4/6 14:50
 */
public abstract class BBBMessageQTO implements Serializable {

    @Data
    @ApiModel("BBBMessageQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO implements Serializable {
        @ApiModelProperty("in会员userId")
        @NotBlank(message = "userId不能为空")
        private String userId;
    }
}
