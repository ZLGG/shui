package com.gs.lshly.middleware.log.aop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.log.IAccessLogService;
import com.gs.lshly.middleware.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 日志拦截
 * @author lxus
 * @since 2020-12-09
 */
@Aspect
@Slf4j
@Component
public class LogAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    static ThreadLocal<BaseDTO> userInfo = new ThreadLocal<>();

    public static void set(BaseDTO baseDTO) {
        log.info("api:{}", JsonUtils.toJson(baseDTO));
        userInfo.set(baseDTO);
    }

    public static BaseDTO get() {
        return userInfo.get();
    }

    public static BaseDTO toDTO(JwtUser jwtUser) {
        return new BaseDTO().setJwtUserId(jwtUser.getId())
                .setJwtUserName(jwtUser.getUsername())
                .setJwtUserType(jwtUser.getType())
                .setJwtShopId(jwtUser.getShopId())
                .setJwtMerchantId(jwtUser.getMerchantId())
                .setJwtWxOpenid(jwtUser.getWxOpenid());
    }

    @Pointcut("@annotation(com.gs.lshly.middleware.log.Log)")
    public void executeLogService() {}

    @Around(value = "executeLogService()")
    public Object handlerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Object result = null;
        long startTime = System.currentTimeMillis();
        if (targetMethod.isAnnotationPresent(Log.class)) {
            Log logMsg = targetMethod.getAnnotation(Log.class);
            AccessLogDTO accessLogDTO = new AccessLogDTO();
            HttpServletRequest httpServletRequest = IpUtil.getHttpServletRequest();

            accessLogDTO.setModule(logMsg.module()).setFunc(logMsg.func())
                    .setClassName(signature.getDeclaringTypeName())
                    .setMethod(httpServletRequest.getMethod())
                    .setRequestUri(httpServletRequest.getRequestURI())
                    .setUserAgent(httpServletRequest.getHeader("user-agent"))
                    .setParams(JSON.toJSONString(httpServletRequest.getParameterMap()))
                    .setRemoteAddr(IpUtil.getRemoteHost(httpServletRequest));
            String params = "";
            Object[] args = pjp.getArgs();
            if(args.length>0){
                if("POST".equals(accessLogDTO.getMethod())){
                    params = JsonUtils.toJson(args);
                }else if("GET".equals(accessLogDTO.getMethod())){
                    params = httpServletRequest.getQueryString();
                }
            }
            accessLogDTO.setParams(params);
            Throwable ee = null;
            try {
                result = pjp.proceed();
                accessLogDTO.setSuccess(true);
            } catch (Throwable e) {
                accessLogDTO.setException(IpUtil.getTrace(e));
                accessLogDTO.setSuccess(false);
                ee = e;
            }
            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            accessLogDTO.setUseTime(elapsedTime);

            log.info("api:{}", JsonUtils.toJson(accessLogDTO));

            if(applicationContext.containsBean("AccessLogService")) {
                IAccessLogService accessLogService = applicationContext.getBean(IAccessLogService.class);
                if (accessLogService != null) {
                    if (!"登陆".equals(accessLogDTO.getModule()) ){
                        accessLogService.save(accessLogDTO);
                    } else  {
                        BaseDTO dto = get();
                        if (dto!=null && StrUtil.isNotBlank(dto.getJwtUserId())) {
                            accessLogService.save(accessLogDTO);
                        }
                    }
                }
            }

            if (ee != null) {
                throw ee;
            }
        }
        return result;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
