package com.gs.lshly.middleware.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxus
 */
@Component
public class AuthLogoutHandler implements LogoutSuccessHandler {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        mapper.writeValue(response.getWriter(), ResponseData.success(CommonConst.LOGOUT_SUCCESS));
    }
}
