package com.gs.lshly.common.struct.bbb.pc.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbUserDTO implements Serializable {

    @Data
    @ApiModel("BbbUserDTO.RegisterETO")
    @Accessors(chain = true)
    public static class RegisterETO extends BaseDTO {

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("密码")
        private String userPwdCfm;

        @ApiModelProperty("手机验证码")
        private String validCode;
    }

    @Data
    @ApiModel("BbbUserDTO.RegisterFromShopETO")
    @Accessors(chain = true)
    public static class RegisterFromShopETO extends BaseDTO {

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("密码")
        private String userPwdCfm;

        @ApiModelProperty("手机验证码")
        private String validCode;

        @ApiModelProperty("注册来源店铺ID(通过商家邀请的时候会有店铺的ID)")
        private String fromShopId;

    }

    @Data
    @ApiModel("BbbUserDTO.LoginETO")
    public static class LoginETO extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "验证码")
        private String validCode;
    }

    @Data
    @ApiModel("BbbUserDTO.UserInfoETO")
    @Accessors(chain = true)
    public static class UserInfoETO extends BaseDTO {

        @ApiModelProperty("昵称")
        private String nick;

        @ApiModelProperty("性别[10=男 20=女 30=保密]")
        private Integer sex;

        @ApiModelProperty("生日")
        private String birthday;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("省代码")
        private String province;

        @ApiModelProperty("市代码")
        private String city;

        @ApiModelProperty("县代码")
        private String county;

        @ApiModelProperty("省名称")
        private String provinceText;

        @ApiModelProperty("市名称")
        private String cityText;

        @ApiModelProperty("县名称")
        private String countyText;

        @ApiModelProperty("详细地址")
        private String realAddress;
    }


    @Data
    @ApiModel("BbbUserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("BbbUserDTO.GetPhoneValidCodeDTO")
    @AllArgsConstructor
    public static class GetPhoneValidCodeDTO extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;
    }


    @Data
    @ApiModel("BbbUserDTO.EditorPasswordETO")
    public static class EditorPasswordETO extends BaseDTO {

        @ApiModelProperty(value = "旧登录密码")
        private String password;

        @ApiModelProperty(value = "新登录密码")
        private String newPassword;

        @ApiModelProperty(value = "新密码确认")
        private String newPasswordCfm;

    }

    @Data
    @ApiModel("BbbUserDTO.EditorPayPasswordDTO")
    public static class EditorPayPasswordDTO extends BaseDTO {

        @ApiModelProperty(value = "登录密码")
        private String password;

        @ApiModelProperty(value = "支付密码")
        private String payPassword;

    }


    @Data
    @ApiModel("BbbUserDTO.BindPhoneDTO")
    public static class BindPhoneDTO extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "手机验证码")
        private String validCode;
    }

    @Data
    @ApiModel("BbbUserDTO.BindEmailDTO")
    public static class BindEmailDTO extends BaseDTO {

        @ApiModelProperty(value = "邮箱号")
        private String email;
    }

    @Data
    @ApiModel("BbbUserDTO.EditorUserNameDTO")
    public static class EditorUserNameDTO extends BaseDTO {

        @ApiModelProperty(value = "用户名")
        private String userName;
    }

    @Data
    @ApiModel("BbbUserDTO.CheckPasswordETO")
    public static class CheckPasswordETO extends BaseDTO {

        @ApiModelProperty(value = "密码")
        private String password;

    }

    @Data
    @ApiModel("BbbUserDTO.ForgetByPhoneETO")
    public static class ForgetByPhoneETO extends BaseDTO {

        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "手机验证码")
        private String validCode;

        @ApiModelProperty(value = "密码")
        private String password;

        @ApiModelProperty(value = "密码")
        private String passwordCfm;

    }

    @Data
    @ApiModel("BbbUserDTO.ForgetEmailETO")
    public static class ForgetEmailETO extends BaseDTO {

        @ApiModelProperty(value = "密码")
        private String password;

        @ApiModelProperty(value = "密码")
        private String passwordCfm;

    }

    @Data
    @ApiModel("BbbUserDTO.PrivateUserInfoDTO")
    public static class PrivateUserInfoDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "会员ID")
        private String userId;
    }

}
