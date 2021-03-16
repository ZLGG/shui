package com.gs.lshly.middleware.auth.rbac;

/**
 * 菜单集合
 * @author lxus
 * @since 2020-12-12
 */
public interface IMenuSet {

    MenuMessage[] getMenus();

    Boolean isUserExpire(String username);

    void setUserExpire(String username);

}
