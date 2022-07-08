package com.citydo.appraisal.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础响应对象
 *
 * @author yangjb
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1712905512361659877L;

    /**
     * 服务执行时间 毫秒
     */
    private long costTime;

    /**
     * 状态码
     */
    private int responseCode;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 响应结果集
     */
    private Object responseData;

    /**
     * 请求方法
     */
    private String action;

    public BaseResponse() {
    }

    public BaseResponse(int responseCode, String responseMsg) {
        super();
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public BaseResponse(int responseCode, String responseMsg, Object responseData) {
        super();
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.responseData = responseData;
    }

    /**
     * 判断是否相应成功
     *
     * @param response
     * @return
     */
    public static boolean isSuccess(BaseResponse response) {
        return response.getResponseCode() == Constant.BaseCode.SUCCESS_CODE;
    }

    /**
     * 失败响应
     */
    public static BaseResponse fail(String responseMsg) {
        return initResponse(responseMsg, Constant.BaseCode.FAIL_CODE, null);
    }

    /**
     * 失败响应
     */
    public static BaseResponse fail() {
        return initResponse("失败", Constant.BaseCode.FAIL_CODE, "");
    }

    /**
     * 失败响应
     */
    public static BaseResponse fail(String responseMsg, int responseCode) {
        return initResponse(responseMsg, responseCode, null);
    }

    /**
     * 失败响应
     */
    public static BaseResponse fail(String responseMsg, Object responseData) {
        return initResponse(responseMsg, 1, responseData);
    }

    /**
     * 失败响应
     */
    public static BaseResponse fail(String responseMsg, int responseCode, Object responseData) {
        return initResponse(responseMsg, responseCode, responseData);
    }

    /**
     * 成功响应
     */
    public static BaseResponse success(String responseMsg) {
        return initResponse(responseMsg, Constant.BaseCode.SUCCESS_CODE, null);
    }

    /**
     * 成功响应
     */
    public static BaseResponse success() {
        return initResponse("成功", Constant.BaseCode.SUCCESS_CODE, "");
    }

    /**
     * 成功响应
     */
    public static BaseResponse success(Object responseData) {
        return initResponse("请求成功", Constant.BaseCode.SUCCESS_CODE, responseData);
    }

    /**
     * 成功响应
     */
    public static BaseResponse success(String responseMsg, Object responseData) {
        return initResponse(responseMsg, Constant.BaseCode.SUCCESS_CODE, responseData);
    }

    /**
     * 成功响应
     */
    public static BaseResponse success(String responseMsg, Object responseData, Integer responseCode) {
        return initResponse(responseMsg, responseCode, responseData);
    }

    /**
     * 结果对象初始化
     */
    private static BaseResponse initResponse(String responseMsg, int responseCode, Object responseData) {
        BaseResponse resp = new BaseResponse(responseCode, responseMsg, responseData);
        return resp;
    }
}
