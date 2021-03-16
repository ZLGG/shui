package com.gs.lshly.common.struct.pos.dto;



import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@Accessors(chain = true)
public class PosRSPurchaseSyncRequestDTO {
    /**
     * 店铺
     * */
    private String shop;
    /**
     * 总部实例id
     * */
    private String masterId;
    /**
     * 供应商id
     * */
    private String suppler;
    /**
     * 供应商名称
     * */
    private String supplerName;
    /**
     * 供应商联系电话
     * */
    private String supplierPhone;
    /**
     * 进货日期
     * */
    private Date purchaseDate;
    /**
     * 进货单单号
     * */
    private String billNum;
    /**
     * 进货单明细
     * */
    private List<RSPurchaseLine> lines;
    /**
     * 备注
     * */
    private String remark;
    @Data
    @Accessors(chain = true)
    public static class RSPurchaseLine {
        /**
         * 商品id
         * */
        private String skuId;
        /**
         * 商品名称
         * */
        private String skuName;
        /**
         * 商品自编码
         * */
        private String code;
        /**
         * 商品条码
         * */
        private String barcode;
        /**
         * 商品售价，新增商品必传
         * */
        private BigDecimal salePrice;
        /**
         * 商品自编码
         * */
        private String munit;
        /**
         * 进货价格
         * */
        private BigDecimal price;
        /**
         * 进货数量
         * */
        private BigDecimal qty;
        /**
         * 进货金额
         * */
        private BigDecimal amount;
    }
}