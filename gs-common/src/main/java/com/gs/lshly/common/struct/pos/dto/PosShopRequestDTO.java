package com.gs.lshly.common.struct.pos.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 10:29 2021/2/23
 */
public abstract class PosShopRequestDTO {

    @Data
    @ApiModel("PosShopRequestDTO.DTO")
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
         * POS店铺名称
         * */
        private String posName;
        /**
         * 店铺地址
         * */
        private String posAddress;

        /**
         * 经度
         * */
        private String lont;


        /**
         * 纬度
         * */
        private String lat;

        /**
         * 法人身份类型（10=中国大陆 20 = 非中国大陆类型）
         */
        private Integer posLegalPersonType;


        /**
         * 法人姓名
         * */
        private String posLegalPerson;

        /**
         * 法人身份证号
         * */
        private String posLegalPersonIdcard;


        /**
         * 法人身份证正反面
         * */
        private String posLegalPersonIdcardImgs;


        /**
         * 营业执照及食品经营许可证
         * */
        private String licenses;

        /**
         * 统一社会信用代码
         * */
        private String unifiedSocialCreditCode;


        /**
         * 手机号
         * */
        private String adminPhone;


        /**
         * 姓名
         * */
        private String adminName;

        /**
         * 邮箱
         * */
        private String adminEmail;


    }


}
