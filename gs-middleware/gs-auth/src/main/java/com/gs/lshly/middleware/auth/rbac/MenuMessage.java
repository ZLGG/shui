package com.gs.lshly.middleware.auth.rbac;

import java.io.Serializable;

/**
 * 菜单-功能枚举
 * @author lxus
 * @since 2020-12-09
 */
public interface MenuMessage extends Serializable {

    String getCode();

    String getName();

    Integer getIndex();
}
