package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantAccountDTO implements Serializable {

    @Data
    @ApiModel("MerchantAccountDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("微信id")
        private String wxOpenid;

        @ApiModelProperty("微信名")
        private String wxName;

        @ApiModelProperty("帐号类型[10=主帐号 20=子帐号]")
        private Integer accountType;

        @ApiModelProperty("帐号状态[10=正常 20=禁用]")
        private Integer accountState;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("MerchantAccountDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("MerchantAccountDTO.ModifyPwdETO")
    @AllArgsConstructor
    public static class ModifyPwdETO extends BaseDTO {

        @ApiModelProperty(value = "帐号ID",hidden = true)
        private String id;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("确认密码")
        private String userPwdCfm;
    }

    @Data
    @ApiModel("MerchantAccountDTO.IdDTO")
    @AllArgsConstructor
    public static class PlatformAccountETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("确认密码")
        private String userPwdCfm;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("用户姓名")
        private String realName;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;


    }

}
