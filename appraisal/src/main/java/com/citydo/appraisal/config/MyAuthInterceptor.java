/**
 * @(#) MgmtAuthInterceptor.java 1.0 2018-08-06
 * Copyright (c) 2018, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.citydo.appraisal.config;

import cn.hutool.json.JSONUtil;
import com.citydo.appraisal.exception.ExceptionEnum;
import com.citydo.appraisal.result.RespResult;
import com.citydo.appraisal.utils.JwtUtil;
import com.citydo.appraisal.utils.ServlertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class MyAuthInterceptor implements HandlerInterceptor {

    private final static String AUTHORIZATION = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        String uri = request.getServletPath();
//        try { //验证Redis中是否有此用户登录
//            String username = JwtUtil.getUsername(token);
//            boolean bo = JwtUtil.verify(request, username, token);
//            if (!bo) {
//                error(response, ExceptionEnum.TOKEN_INVALID);
//                return false;
//            }
//        } catch (Exception e) {
//            log.error("登录失败：error:e", e);
//            error(response, ExceptionEnum.TOKEN_INVALID);
//            //调用下面的方法向客户端返回错误信息
//            return false;
//        }
        return true;
    }

    /**
     * 验证错误
     *
     * @param response
     */
    private void error(HttpServletResponse response, ExceptionEnum exceptionEnum) {
        String msg = JSONUtil.toJsonStr(RespResult.failure(exceptionEnum.getMessage(), exceptionEnum.getCode()));
        response.setStatus(exceptionEnum.getCode());
        ServlertUtil.outputSteam(msg, response);
    }
}
