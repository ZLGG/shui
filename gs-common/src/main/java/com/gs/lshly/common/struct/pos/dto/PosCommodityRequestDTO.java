package com.gs.lshly.common.struct.pos.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Starry
 * @Date 18:20 2021/2/22
 */
public abstract class PosCommodityRequestDTO {


    @Data
    @ApiModel("PosCommodityRequestDTO.DTO")
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
         * 店铺商品sku 69码
         * */
        private String posSKU69Code;
        /**
         * 商品sku名称
         * */
        private String name;

        /**
         * 店铺商品spuId
         * */
        private String spuId;

        /**
         * 商品规格名称
         * */
        private String specName;

        /**
         * 商品规格值
         * */
        private String specValue;

        /**
         * 商品图片
         * */
        private String images;


        /**
         * 商品价格
         * */
        private Double price;


        /**
         * 库存变动流水号
         * */
        private String stockChangeSerialNo;

        /**
         * 库存总量
         * */
        private Integer totalStock;


        /**
         * 签名
         * */
        private String sign;


    }
}
