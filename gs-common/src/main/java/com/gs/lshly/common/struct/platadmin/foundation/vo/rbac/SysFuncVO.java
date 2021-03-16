package com.gs.lshly.common.struct.platadmin.foundation.vo.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/12/12
 */
public abstract class SysFuncVO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("设置前端路由显示对象")
    public static class List implements Serializable {

        @ApiModelProperty(value = "菜单功能id")
        private String id;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "图标")
        private String icon;

        @ApiModelProperty(value = "顺序")
        private String index;

        @ApiModelProperty("路由")
        private String frontRouter;

    }

}
