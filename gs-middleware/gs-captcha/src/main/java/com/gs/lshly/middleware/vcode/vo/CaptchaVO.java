package com.gs.lshly.middleware.vcode.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CaptchaVO implements Serializable {
    /**
     * 验证码标识符
     */
    private String captchaKey;
    /**
     * base64字符串
     */
    private String base64Img;
}
