package com.gs.lshly.common.struct.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 * @since 2020-12-12
 */
public class PermitNodeVO implements Serializable {

    @Data
    @ApiModel("用户权限集")
    @Accessors(chain = true)
    public static class CheckedNodeVO implements Serializable {

        @ApiModelProperty(value = "用户设置权限id")
        private String funcId;

    }

    @Data
    @ApiModel("系统功能模块树")
    @Accessors(chain = true)
    public static class PermitNodeTreeVO implements Serializable {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "名称")
        private String label;

        @ApiModelProperty(value = "前端路由")
        private String frontRouter;

        @ApiModelProperty(value = "子节点")
        List<PermitNodeTreeVO> children;

    }

}
