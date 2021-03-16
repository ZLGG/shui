package com.gs.lshly.common.struct.bbc.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserDTO implements Serializable {

    @Data
    @ApiModel("BBC.UserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("头像")
        private String headImg;

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

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("性别[10=男  20=女]")
        private Integer sex;

        @ApiModelProperty("注册IP")
        private String regIp;

        @ApiModelProperty("法人单位ID")
        private String legalId;

        @ApiModelProperty("会员等级名称")
        private String leveName;

        @ApiModelProperty("注册来源店铺ID")
        private String fromShopId;

        @ApiModelProperty(value = "会员类型[10=2b 20=2c]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "会员等级ID",hidden = true)
        private String leveId;

        @ApiModelProperty(value = "会员状态[10=可用 20=禁用]",hidden = true)
        private Integer state;
    }

    @Data
    @ApiModel("BBC.UserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "会员ID")
        private String id;
    }

    @Data
    @ApiModel("BBC.UserDTO.IdListDTO")
    @AllArgsConstructor
    public static class RegditerDTO extends BaseDTO {
        @ApiModelProperty(value = "会员ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("BBC.UserDTO.IdListDTO")
    @Accessors(chain = true)
    public static class UpdatePwdDTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID",hidden = true)
        private String userId;

        @ApiModelProperty(value = "等级ID")
        private String leveId;
    }

}
