package com.gs.lshly.common.struct.platadmin.foundation.dto.rbac;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 * @since 2020/12/12
 */
public abstract class SysRoleDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("平台角色新增编辑对象")
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "角色id", hidden = true)
        private String id;

        @ApiModelProperty("登陆名")
        private String name;

        @ApiModelProperty("备注")
        private String remark;

    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("角色id")
        private String id;
    }

    @Data
    @ApiModel("平台角色设置权限对象")
    public static class RoleFuncETO extends BaseDTO {

        @ApiModelProperty(value = "角色id", hidden = true)
        private String roleId;

        @ApiModelProperty("权限id集")
        private List<String> funcIds;
    }

}
