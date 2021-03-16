package com.gs.lshly.common.struct.fy.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/26 下午10:16
 */
public abstract class ActivityUserVO implements Serializable {

    @Data
    @ApiModel("ActivityUserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("标签")
        private String labelName;

        @ApiModelProperty("所属工会")
        private String labourUnion;
    }
}
