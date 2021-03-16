package com.gs.lshly.middleware.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.lshly.common.constants.CommonConst;
import com.gs.lshly.common.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxus
 */
@Component
@Slf4j
public class AuthFailedHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String exceptionName = exception.getClass().getName();
        String responseMsg = exception.getMessage() + (exception.getCause()!=null ? ":"+exception.getCause().getMessage() : "");
        int responseCode = HttpStatus.UNAUTHORIZED.value();
        if(exception.getCause()!=null && exception.getCause() instanceof ExpiredJwtException) {
            exceptionName = ExpiredJwtException.class.getName();
            responseMsg = CommonConst.ACCOUNT_EXPIRED;
        } else if(exception.getCause()!=null && exception.getCause() instanceof NestedServletException) {
            exceptionName = exception.getCause().getCause().getClass().getName();
            responseMsg = exception.getCause().getCause().getMessage();
        } else if (exception instanceof DisabledException) {
            responseCode = HttpStatus.OK.value();
            responseMsg = CommonConst.ACCOUNT_DISABLED;
        } else if(exception instanceof BadCredentialsException) {
            responseMsg = CommonConst.USERNAME_OR_PASSWORD_INCORRECT;
        } else if(exception instanceof SessionAuthenticationException) {
            responseMsg = CommonConst.ACCOUNT_PREVENT_LOGIN;
        }
        log.info("认证异常:" + exceptionName + ":" + responseMsg);

        response.setStatus(responseCode);
        mapper.writeValue(response.getWriter(), ResponseData.authFail(responseMsg));
    }

}
