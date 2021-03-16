package com.gs.lshly.common.exception;

import org.apache.dubbo.rpc.RpcException;
import org.springframework.http.HttpStatus;

/**
 * 业务异常
 * @author lxus
 * @since 2020/9/14
 */
public class BusinessException extends RpcException {

    private int code = 300;

    private HttpStatus httpStatus = HttpStatus.OK;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
