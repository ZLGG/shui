package com.gs.lshly.middleware.log;

import java.lang.annotation.*;

/**
 * @author lxus
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {

    /**
     * 模块
     */
    String module() default "";

    /**
     * 功能
     */
    String func() default "";
}
