package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* @author xxfc
* @since 2020-10-23
*/
public abstract class PCMerchMerchantAccountDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantAccountDTO.RegDTO")
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
    @ApiModel("PCMerchMerchantAccountDTO.AddDTO")
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

        @ApiModelProperty("联系地址")
        private String address;

        @ApiModelProperty("商户名称")
        private String name;

        @ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;

        @ApiModelProperty("商户属地（省名称）")
        private String province;

        @ApiModelProperty("商户属地（市名称）")
        private String city;

        @ApiModelProperty("协议到期时间")
        private Date expirationTime;

        @ApiModelProperty("协议号")
        private String agreementCode;

        @ApiModelProperty("供应商纳税性质(10=一般纳税人 20=小规模纳税人)")
        private Integer taxType;

        @ApiModelProperty("税率(%)")
        private Double taxRate;
    }

    @Data
    @ApiModel("PCMerchMerchantAccountDTO.ETO")
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

        @ApiModelProperty("联系地址")
        private String address;

        @ApiModelProperty("商户名称")
        private String name;

        @ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;

        @ApiModelProperty("商户属地（省名称）")
        private String province;

        @ApiModelProperty("商户属地（市名称）")
        private String city;

        @ApiModelProperty("协议到期时间")
        private Date expirationTime;

        @ApiModelProperty("协议号")
        private String agreementCode;

        @ApiModelProperty("供应商纳税性质(10=一般纳税人 20=小规模纳税人)")
        private Integer taxType;

        @ApiModelProperty("税率(%)")
        private Double taxRate;
    }

    @Data
    @ApiModel("PCMerchMerchantAccountDTO.PassworldETO")
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
    @ApiModel("PCMerchMerchantAccountDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }




    @Data
    @ApiModel("PCMerchMerchantAccountDTO.RegisterPhoneETO")
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
    @ApiModel("PCMerchMerchantAccountDTO.RegisterETO")
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
    @ApiModel("PCMerchMerchantAccountDTO.LoginETO")
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
    @ApiModel("PCMerchMerchantAccountDTO.CheckUserNameDTO")
    @AllArgsConstructor
    public static class CheckUserNameDTO extends BaseDTO {

        @ApiModelProperty(value = "用户名")
        private String userName;

    }

}
