package com.gs.lshly.common.struct.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 * @since 2020-10-26
 */
public class OAuth2VO implements Serializable {



    @Data
    @ApiModel("TokenVO")
    @Accessors(chain = true)
    public static class TokenVO implements Serializable {

        @ApiModelProperty(value = "token")
        private String token;

        @ApiModelProperty(value = "token过期时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime tokenExpire;

        @ApiModelProperty(value = "刷新token")
        private String refreshToken;

        @ApiModelProperty(value = "刷新token")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime refreshTokenExpire;

    }

    @Data
    @ApiModel("SysUserVO")
    @Accessors(chain = true)
    public static class SysUserVO implements Serializable {

        @ApiModelProperty(value = "用户id")
        private String userId;

        @ApiModelProperty(value = "用户登录名")
        private String userName;

        @ApiModelProperty(value = "用户头像")
        private String headImg;

    }

    @Data
    @ApiModel("MerchantVO")
    @Accessors(chain = true)
    public static class MerchantVO implements Serializable {

        @ApiModelProperty(value = "用户id")
        private String userId;

        @ApiModelProperty(value = "用户登录名")
        private String userName;

        @ApiModelProperty(value = "用户头像")
        private String headImg;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("平台用户id")
        private String sysUserId;

        @ApiModelProperty(value = "平台用户登录名")
        private String sysUserName;

        @ApiModelProperty(value = "平台用户头像")
        private String sysHeadImg;

    }

}
