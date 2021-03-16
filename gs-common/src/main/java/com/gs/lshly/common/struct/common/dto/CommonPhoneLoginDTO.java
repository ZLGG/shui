package com.gs.lshly.common.struct.common.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 手机登陆DTO
 * @author lxus
 * @since 2020-10-28
 */
public class CommonPhoneLoginDTO {

    @Data
    @AllArgsConstructor
    public static class GetPhoneValidCode extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;
    }

    @Data
    public static class Login extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "验证码")
        private String validCode;
    }

    @Data
    public static class WxUserPhone implements Serializable {

        private String appId;
        private String openId;
        private String phoneNumber;
        private String purePhoneNumber;
        private String countryCode;

    }

}
