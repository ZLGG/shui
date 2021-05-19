package com.gs.lshly.middleware.auth.security;


import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.constants.SecurityConstants;
import com.gs.lshly.common.utils.AES;
import com.gs.lshly.middleware.vcode.kaptcha.CaptchaService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证
 * @author lxus
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    private AuthenticationSuccessHandler successHandler;

    private AuthenticationFailureHandler failedHandler;

    private CaptchaService captchaService;

    public JwtAuthenticationFilter() { }

    public JwtAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, CaptchaService captchaService) {
        this.successHandler = successHandler;
        this.failedHandler = failureHandler;
        this.captchaService = captchaService;
        super.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = this.obtainUsername(request)!=null ? this.obtainUsername(request).trim() : "";
        String password = this.obtainPassword(request)!=null ? this.obtainPassword(request) : "";
        
        if(StringUtils.isNotEmpty(password)&&password.length()>10&&!password.startsWith("12")){
        	password = AES.Decrypt(password);
        }
        String remember = request.getParameter("remember-me");
        rememberMe.set(StrUtil.isNotBlank(remember) && ("1".equals(remember) || "true".equals(remember)));
        String vcId = request.getParameter("vcId");
        String vcode = request.getParameter("vcode");
//        if(captchaService.match(vcId, vcode)) {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            return this.getAuthenticationManager().authenticate(authRequest);
//        } else {
//            throw new CaptchaNotMatchException("验证码错误");
//        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if(successHandler == null) {
            super.successfulAuthentication(request,response,chain,authResult);
        }  else {
            SecurityContextHolder.getContext().setAuthentication(authResult);
            this.successHandler.onAuthenticationSuccess(request, response, authResult);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if(failedHandler == null) {
            super.unsuccessfulAuthentication(request, response, failed);
        } else {
            SecurityContextHolder.clearContext();
            this.failedHandler.onAuthenticationFailure(request, response, failed);
        }
    }
}
