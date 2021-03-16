package com.gs.lshly.common.struct.platadmin.foundation.vo.rbac;

import com.gs.lshly.common.annotation.ExportProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/12/12
 */
public abstract class SysRoleVO implements Serializable {

    @Data
    @ApiModel("平台角色列表")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty("平台角色id")
        @ExportProperty(hide = true)
        private String id;

        @ApiModelProperty(value = "角色名", position = 1)
        private String name;

        @ApiModelProperty("备注")
        private String remark;

    }

    @Data
    @ApiModel("平台管理账号明细")
    public static class DetailVO extends ListVO {

    }


}
