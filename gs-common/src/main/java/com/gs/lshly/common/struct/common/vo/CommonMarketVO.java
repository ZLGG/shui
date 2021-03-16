package com.gs.lshly.common.struct.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxus
 * @since 2021-2-5
 */
public abstract class CommonMarketVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ActiveVO implements Serializable {

        @ApiModelProperty("营销活动名称")
        private String activeName;
    }

    @Data
    @Accessors(chain = true)
    public static class UserCardVO implements Serializable {
        @ApiModelProperty("优惠券名称")
        private String cardName;
    }

}
