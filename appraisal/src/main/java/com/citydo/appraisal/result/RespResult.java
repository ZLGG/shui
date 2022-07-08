package com.citydo.appraisal.result;

import lombok.Data;


@Data
public class RespResult<T>  {

    /** 请求结果：true-成功，false-失败 */
    private boolean success = false;

    /** 错误提示 */
    private String message;

    /** 响应码 */
    private Integer code;

    /** 业务数据 */
    private T data;

    public static <T> RespResult<T> success() {
        RespResult<T> result = new RespResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> RespResult<T> success(T entry) {
        RespResult<T> result = new RespResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setData(entry);
        return result;
    }

    public static <T> RespResult<T> failure(String errorMsg) {
        return failure(errorMsg, ResultCode.ERROR);
    }
    public static <T> RespResult<T> failure(String errorMsg, Integer errorCode) {
        RespResult<T> result = new RespResult<>();
        result.setSuccess(false);
        result.setCode(errorCode);
        result.setMessage(errorMsg);
        return result;
    }
}
