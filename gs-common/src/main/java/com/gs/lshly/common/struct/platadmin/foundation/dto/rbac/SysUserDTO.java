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
 * @since 2020/09/14
 */
public abstract class SysUserDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("平台账号新增编辑对象")
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "管理平台用户id", hidden = true)
        private String id;

        @ApiModelProperty("登陆名")
        private String name;

        @ApiModelProperty("密码")
        private String pwd;

        @ApiModelProperty("状态")
        private Integer state;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("账号类型")
        private Integer type;
    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("管理平台用户id")
        private String id;
    }

    @Data
    @Accessors(chain = true)
    public static class EditPasswordDTO extends BaseDTO {

        @ApiModelProperty("管理平台用户id")
        private String id;

        @ApiModelProperty("旧密码")
        private String oldPwd;

        @ApiModelProperty("新密码")
        private String newPwd;

        @ApiModelProperty("确认密码")
        private String confirmPwd;
    }

    @Data
    @ApiModel("平台账号关联角色参数对象")
    public static class UserRoleETO extends BaseDTO {

        @ApiModelProperty(value = "管理平台用户id" ,hidden = true)
        private String userId;

        @ApiModelProperty("角色id集")
        private List<String> roleIds;

    }
}
