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
 * @create 2021/3/22 14:22
 */
public class BbbInUserCouponQTO implements Serializable {
    @Data
    @ApiModel("BbbInUserCouponQTO.QTO")
    @Accessors(chain = true)
    public static class QTO implements Serializable {
        @ApiModelProperty("in会员userId")
        @NotBlank(message = "userId不能为空")
        private String userId;
    }
}
