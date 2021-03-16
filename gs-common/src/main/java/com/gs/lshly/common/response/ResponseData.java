package com.gs.lshly.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/9/14
 */
@Data
@ApiModel(value="ResponseData", description="返回信息")
public class ResponseData<T> implements Serializable {

    public static final int BUSINESS_EXCEPTION_CODE = 500;

    public static final int AUTH_EXCEPTION_CODE = 300;

    public static final int SUCCESS_CODE = 200;

    private ResponseData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @ApiModelProperty(value="状态码 data: 200 fail: 300")
    private int code;

    @ApiModelProperty("提示信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public boolean isSuccess(){
        return this.code == 200;
    }

    public static ResponseData success() {
        return ResponseData.success("success");
    }

    public static ResponseData fail() {
        return ResponseData.fail("fail");
    }

    public static ResponseData fail(String message) {
        return new ResponseData(BUSINESS_EXCEPTION_CODE, message);
    }
    public static <T> ResponseData fail(String message, T messageData) {
        return new ResponseData(BUSINESS_EXCEPTION_CODE, message, messageData);
    }

    public static ResponseData authFail(String message) {
        return new ResponseData(AUTH_EXCEPTION_CODE, message);
    }

    public static <T> ResponseData data(T data) {
        return new ResponseData(SUCCESS_CODE, "success", data);
    }

    public static ResponseData success(String message) {
        return new ResponseData(SUCCESS_CODE, message);
    }
}
