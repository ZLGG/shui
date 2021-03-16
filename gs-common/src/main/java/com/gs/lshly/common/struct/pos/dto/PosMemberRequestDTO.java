package com.gs.lshly.common.struct.pos.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 10:52 2021/2/23
 */
public abstract class PosMemberRequestDTO {

    @Data
    @ApiModel("PosMemberRequestDTO.DTO")
    @Accessors(chain = true)
    public static class DTO implements Serializable {
        /**
         * 当前时间毫秒数
         * */
        private Long timestamp;
        /**
         * 6位随机字符
         * */
        private String nonce;
        /**
         * POS店编号
         * */
        private String posCode;
        /**
         * 手机号
         * */
        private String phone;
        /**
         * 昵称
         * */
        private String nickName;

        /**
         * 签名
         * */
        private String sign;

    }
}
