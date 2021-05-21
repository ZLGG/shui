package com.gs.lshly.common.struct.platadmin.foundation.vo.rbac;

import com.gs.lshly.common.annotation.ExportProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class SysUserVO implements Serializable {

    @Data
    @ApiModel("平台管理账号列表")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty("管理平台用户id")
        @ExportProperty(hide = true)
        private String id;

        @ApiModelProperty(value = "登陆名", position = 1)
        private String name;

        @ApiModelProperty("密码")
        @ExportProperty(hide = true)
        private String pwd;

        @ApiModelProperty(value = "状态(10-启用,20-停用)")
        @ExportProperty(value = "账号状态", enumFullName = "com.gs.lshly.biz.support.platadmin.enums.SysUserStateEnum", position = 2)
        private Integer state;

        @ApiModelProperty("头像")
        @ExportProperty(hide = true)
        private String headImg;

        @ApiModelProperty("账号类型")
        @ExportProperty(value = "账号类型", enumFullName = "com.gs.lshly.biz.support.platadmin.enums.SysUserTypeEnum", position = 3)
        private Integer type;

        @ApiModelProperty(value = "微信openid", position = 4)
        private String openid;

        @ApiModelProperty(value = "微信名", position = 5)
        private String wxname;

        @ApiModelProperty("微信头像")
        @ExportProperty(hide = true)
        private String wxheadimg;

        @ApiModelProperty(value = "手机号",position = 6)
        private String phone;
    }

    @Data
    @ApiModel("平台管理账号明细")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("账号角色")
    public static class UserRoleVO implements Serializable {

        @ApiModelProperty("角色id")
        private String roleId;

        @ApiModelProperty("角色名")
        private String roleName;

    }
}
