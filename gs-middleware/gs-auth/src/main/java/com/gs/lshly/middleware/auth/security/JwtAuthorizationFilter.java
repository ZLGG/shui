package com.gs.lshly.middleware.auth.security;


import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.constants.SecurityConstants;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.auth.rbac.IMenuSet;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权
 * @author lxus
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private AuthenticationFailureHandler failureHandler;

    private String[] authURI;

    private IMenuSet authBean;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler failureHandler, String[] authURI, IMenuSet authBean) {
        super(authenticationManager);
        this.failureHandler = failureHandler;
        this.authURI = authURI;
        this.authBean = authBean;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        // 非认证路径，则不校验jwt
        if (unNeedAuth(request.getRequestURI())
        // 非oauth认证路径
                || request.getRequestURI().contains("/oauth/")) {
            //允许会话过期，适用场景（商品详情页中的用户关注状态，在非认证接口中，会话token不影响接口调用成功，但影响返回的数据）
            authentication(request, response, true);
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            authentication(request, response, false);
            super.doFilterInternal(request, response, chain);
        } catch (Exception e) {
            e.printStackTrace();
            failureHandler.onAuthenticationFailure(request, response, e instanceof AuthenticationException ? (AuthenticationException) e : new AuthenticationException("业务异常", e) {});
        }
    }

    /**
     * 不需要验证路径
     * @param requestURI
     * @return
     */
    private boolean unNeedAuth(String requestURI) {
        for (String uri : authURI) {
            if (requestURI.contains(uri)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这里从token中获取用户信息并新建一个token
     * @return
     */
    private void authentication(HttpServletRequest request, HttpServletResponse response, boolean allowExpireOrEmpty) {
        String tokenHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (StrUtil.isBlank(tokenHeader)) {
            tokenHeader = request.getParameter("token");
        }
        if (StrUtil.isBlank(tokenHeader)) {
            if (allowExpireOrEmpty) {
                return;
            } else {
                throw new CredentialsExpiredException(CommonConst.UNAUTHORIZED);
            }
        }

        String token = tokenHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

        JwtUser jwtUser = null;
        try {
            jwtUser = JwtUtil.getJwtUser(token);
        } catch (Exception e) {
            if (allowExpireOrEmpty) {
                return;
            } else {
                throw e;
            }
        }
        if (!JwtUtil.isExpiration(jwtUser)) {
            if (allowExpireOrEmpty) {
                return;
            } else {
                throw new CredentialsExpiredException(CommonConst.ACCOUNT_EXPIRED);
            }
        }
        activeJwtToken(jwtUser, response);
    }

    private void activeJwtToken(JwtUser jwtUser, HttpServletResponse response) {
        //token即将过期，通知前端更换
        if (true || JwtUtil.nearExpire(jwtUser)) {
            response.setHeader("refresh_token", JwtUtil.createToken(jwtUser));
        }
        //token被手动设置过期
        if (authBean.isUserExpire(jwtUser.getUsername())) {
            jwtUser = (JwtUser) ((UserDetailsService) authBean).loadUserByUsername(jwtUser.getUsername());
            response.setHeader("refresh_token", JwtUtil.createToken(jwtUser));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUser.getUsername(), null, jwtUser.getAuthorities());
        authenticationToken.setDetails(jwtUser);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
