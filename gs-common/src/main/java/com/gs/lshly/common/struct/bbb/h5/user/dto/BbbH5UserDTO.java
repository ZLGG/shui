package com.gs.lshly.common.struct.bbb.h5.user.dto;

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
public abstract class BbbH5UserDTO implements Serializable {

    @Data
    @ApiModel("BbcUserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("会员状态[10=可用 20=禁用]")
        private Integer state;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer type;

        @ApiModelProperty("微信ID")
        private String openid;

        @ApiModelProperty("微信名")
        private String wxname;

        @ApiModelProperty("微信头像")
        private String wxheadimg;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("生日")
        private String birthday;

        @ApiModelProperty("省/市/县")
        private String region;

        @ApiModelProperty("性别[10=男  20=女]")
        private Integer sex;

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("注册IP")
        private String regIp;

        @ApiModelProperty("法人单位ID")
        private String legalId;

        @ApiModelProperty("会员等级ID")
        private String leveId;

        @ApiModelProperty("会员等级名称")
        private String leveName;

        @ApiModelProperty("注册来源店铺ID")
        private String fromShopId;
    }
    @Data
    @ApiModel("BbcUserDTO.UserInfoETO")
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
    @ApiModel("BbcUserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("BbcUserDTO.GetPhoneValidCodeDTO")
    public static class GetPhoneValidCodeDTO extends BaseDTO {
        @ApiModelProperty(value = "手机号码")
        private String phone;
    }

    @Data
    @ApiModel("BbcUserDTO.LoginETO")
    public static class LoginETO extends BaseDTO {
        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "验证码")
        private String validCode;
    }

    @Data
    @ApiModel("BbcUserDTO.WxLoginETO")
    @AllArgsConstructor
    public static class WxLoginETO extends BaseDTO {

    }

}
