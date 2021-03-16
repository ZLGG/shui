package com.gs.lshly.middleware.auth.rbac;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @author lxus
 * @since 2020-12-08
 * 功能模块类型
 */
public enum PermitNodeTypeEnum implements EnumMessage{
    根节点(10,"实名"),
    模块(20,"模块"),
    功能(30,"功能");

    private Integer code;
    private String remark;

    PermitNodeTypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
