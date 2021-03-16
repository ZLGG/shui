package com.gs.lshly.common.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * aop工具类
 * @author lxus
 * @since 2020-12-10
 */
public class AopUtil {
    public static Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        return methodSignature.getMethod();
    }
}
