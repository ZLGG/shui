package com.gs.lshly.middleware.auth.rbac;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块-菜单-功能
 * @author lxus
 * @since 2020-12-08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Func {

    String code();

    String name();

    String icon() default "";

    int index() default 0;

}
