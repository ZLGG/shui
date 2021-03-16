package com.gs.lshly.middleware.auth.security.handler;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.auth.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author lxus
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();


        jwtUser.setRememberMe(JwtAuthenticationFilter.rememberMe.get());
        JwtAuthenticationFilter.rememberMe.remove();

        String token = JwtUtil.createToken(jwtUser);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        Map map = CollUtil.newHashMap(2);
        map.put("Authorization", token);
        map.put("Permit", jwtUser.getPermitNode());
        mapper.writeValue(response.getWriter(), ResponseData.data(map));
    }
}
