package com.citydo.appraisal.exception;

import com.citydo.appraisal.result.ResultCode;
import lombok.Data;


@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -254668579697451945L;

    /**
     * 错误代码
     */
    private int errorCode;

    /**
     * 错误提示信息
     */
    private String errorMsg;


    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorCode = ResultCode.ERROR;
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
