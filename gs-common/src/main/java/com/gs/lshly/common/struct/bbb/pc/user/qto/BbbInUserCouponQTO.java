package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Data
    @ApiModel("BbbInUserCouponQTO.BuyCouponQTO")
    @Accessors(chain = true)
    public static class BuyCouponQTO implements Serializable {
        @ApiModelProperty("in会员userId")
        @NotBlank(message = "userId不能为空")
        private String userId;

        @ApiModelProperty("会员类型（0-年度会员，1-月度会员）")
        @NotNull(message = "会员类型不能为空")
        private Integer vipType;
    }

}
