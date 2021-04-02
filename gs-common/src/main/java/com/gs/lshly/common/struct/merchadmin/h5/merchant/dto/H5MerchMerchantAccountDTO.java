package com.gs.lshly.common.struct.merchadmin.h5.merchant.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zst
* @since 2021-03-12
*/
public abstract class H5MerchMerchantAccountDTO implements Serializable {

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.RegDTO")
    @Accessors(chain = true)
    public static class RegDTO extends BaseDTO {

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("密码")
        private String userPwdCfm;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

    }


    @Data
    @ApiModel("H5MerchMerchantAccountDTO.AddDTO")
    @Accessors(chain = true)
    public static class AddDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("密码")
        private String userPwdCfm;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("角色ID")
        private String roleId;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("角色ID")
        private String roleId;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.PassworldETO")
    @Accessors(chain = true)
    public static class PassworldETO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("密码")
        private String userPwdCfm;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }




    @Data
    @ApiModel("H5MerchMerchantAccountDTO.RegisterPhoneETO")
    @Accessors(chain = true)
    public static class RegisterPhoneDTO extends BaseDTO {

        @ApiModelProperty("图形验证码")
        private String imageCheckCode;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("手机验证码")
        private String phoneCheckCode;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.RegisterETO")
    @Accessors(chain = true)
    public static class RegisterDTO extends BaseDTO {

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("确认密码")
        private String userPwdCfm;

        @ApiModelProperty("联系人姓名")
        private String realName;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;
    }

    @Data
    @ApiModel("H5MerchMerchantAccountDTO.LoginETO")
    @Accessors(chain = true)
    public static class LoginETO extends BaseDTO {

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("图形验证码")
        private String imageCheckCode;

        @ApiModelProperty("手机验证码")
        private String phoneCheckCode;

    }


    @Data
    @ApiModel("H5MerchMerchantAccountDTO.CheckUserNameDTO")
    @AllArgsConstructor
    public static class CheckUserNameDTO extends BaseDTO {

        @ApiModelProperty(value = "用户名")
        private String userName;

    }

}
