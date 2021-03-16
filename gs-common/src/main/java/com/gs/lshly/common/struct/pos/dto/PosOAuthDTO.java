package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

public abstract class PosOAuthDTO {

    @Data
    @ApiModel("PosOAuthDTO.TokenDTO")
    @Accessors(chain = true)
    public static class TokenDTO extends BaseDTO {

        /**
         * token
         */
        private String id;

        /**
         * 过期时间
         */
        private Long expire;

        /**
         * 刷新token
         */
        private String refreshToken;

        /**
         * 刷新过期时间
         */
        private Long reExpire;

        /**
         * 用户id
         */
        private String userId;

        /**
         * 用户昵称
         */
        private String userNick;

        /**
         * 上级用户id
         */
        private String parentId;

        /**
         * key
         */
        private String appKey;

        /**
         * secret
         */
        private String appSecret;
    }

}
