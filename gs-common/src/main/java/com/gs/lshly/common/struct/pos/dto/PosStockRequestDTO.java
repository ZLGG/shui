package com.gs.lshly.common.struct.pos.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:00 2021/2/23
 */
public abstract class PosStockRequestDTO {

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
         * 店铺商品sku 69
         * */
        private String posSKU69Code;
        /**
         * 库存变动流水号
         * */
        private String stockChangeSerialNo;

        /**
         * 库存变化量
         * */
        private Integer stockChangeAmount;


        /**
         * 库存总量
         * */
        private Integer totalStock;


        /**
         * 是否刷新总库存	0-不刷新；1-刷新平台库存总量。
         * */
        private Integer flushTotalStock;

        /**
         * 变更单据图片
         * */
        private String changeCertificate;


        /**
         * 签名
         * */
        private String sign;

    }
}
