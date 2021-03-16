package com.gs.lshly.middleware.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxus
 */
@Component
@Slf4j
public class AuthDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        log.info("auth entry point exception:"+accessDeniedException.getClass().getName());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            mapper.writeValue(response.getWriter(), ResponseData.authFail(CommonConst.UNAUTHORIZED));
        }
    }
}
