package com.gs.lshly.common.struct.fy.activity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/26 下午10:14
 */
public abstract class ActivityUserQTO implements Serializable {

    @Data
    @ApiModel("ActivityUserQTO.QTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("活动id")
        private String activityId;

        @ApiModelProperty("会员用户名")
        private String userName;
    }
}
