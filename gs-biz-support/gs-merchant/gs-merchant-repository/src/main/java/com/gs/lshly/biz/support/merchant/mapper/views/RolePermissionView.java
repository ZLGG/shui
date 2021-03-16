package com.gs.lshly.biz.support.merchant.mapper.views;

import lombok.Data;

@Data
public class RolePermissionView {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限id
     */
    private String id;

    /**
     * 组枚举编号
     */
    private Integer groupCode;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 路由地址
     */
    private String route;
}
