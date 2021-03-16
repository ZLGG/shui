package com.gs.lshly.middleware.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxus
 */
@Component
@Slf4j
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info("auth entry point exception:"+authException.getClass().getName());

        String responseMsg = authException.getMessage();
        if (authException instanceof InsufficientAuthenticationException) {
            responseMsg = CommonConst.UNAUTHORIZED;
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        mapper.writeValue(response.getWriter(), ResponseData.authFail(responseMsg));
    }

}
