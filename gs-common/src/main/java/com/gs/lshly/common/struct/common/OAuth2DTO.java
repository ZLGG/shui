package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020-10-26
 */
public class OAuth2DTO implements Serializable {

    @Data
    @ApiModel("AllocateCodeDTO")
    @Accessors(chain = true)
    public static class AllocateCodeDTO extends BaseDTO {

        @ApiModelProperty(value = "登录终端id")
        private String clientId;

    }

    @Data
    @ApiModel("GenerationToken")
    @Accessors(chain = true)
    public static class GenerationTokenDTO extends BaseDTO {
        @ApiModelProperty(value = "code")
        private String code;
    }

    @Data
    @ApiModel("UserInfoDTO")
    @Accessors(chain = true)
    public static class UserInfoDTO extends BaseDTO {
        @ApiModelProperty(value = "token")
        private String token;
    }

    @Data
    @ApiModel("RefreshTokenDTO")
    @Accessors(chain = true)
    public static class RefreshTokenDTO extends BaseDTO {
        @ApiModelProperty(value = "refreshToken")
        private String refreshToken;

    }
}
