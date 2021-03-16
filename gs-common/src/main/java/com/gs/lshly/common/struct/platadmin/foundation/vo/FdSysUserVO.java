package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class FdSysUserVO implements Serializable {

    @Data
    @ApiModel("SysUserVO.FdSysUserVO")
    @Accessors(chain = true)
    public static class SimpleVO implements Serializable {

        @ApiModelProperty("系统管理用户ID")
        private String id;

        @ApiModelProperty("用户名")
        private String name;

        @ApiModelProperty("微信名")
        private String wxname;

    }

}
