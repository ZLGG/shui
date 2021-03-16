package com.gs.lshly.common.exception;


import com.gs.lshly.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author lxus
 * @since 2020/11/08
 */
@RestControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    //数据校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData bindValidExceptionHandler(MethodArgumentNotValidException e){
        return wrapMsg( e.getBindingResult() );
    }

    //数据校验失败
    @ExceptionHandler(BindException.class)
    public ResponseData bindExceptionHandler(BindException e){
        return wrapMsg( e.getBindingResult() );
    }

    private ResponseData wrapMsg(BindingResult result) {
        return ResponseData.fail("数据格式错误", result);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseData bindBizExceptionHandler(BusinessException e) {
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseData bindExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseData.fail("系统繁忙,请联系管理员", e.getMessage());
    }

}
