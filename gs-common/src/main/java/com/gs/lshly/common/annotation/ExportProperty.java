package com.gs.lshly.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lxus
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface ExportProperty {

    /**
     * 标题
     */
    String value() default "";

    /**
     * 枚举
     */
    String enumFullName() default "";

    /**
     * 导出时隐藏该列
     */
    boolean hide() default false;

    /**
     * 顺序
     */
    int position() default 0;
}
