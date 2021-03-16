package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


public abstract class PosTradeStateRequestDTO {

    @Data
    @ApiModel("PosTradeStateRequestDTO.DTO")
    @Accessors(chain = true)
    public static class DTO  implements Serializable {
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
         * 订单编号
         * */
        private String posOrderNo;
        /**
         * 订单状态
         * */
        private String posOrderState;
        /**
         * 物流公司
         * */
        private String deliveryCompany;
        /**
         * 物流公司编号
         * */
        private String deliveryCompanyCode;
        /**
         * 物流编号
         * */
        private String deliveryCode;
        /**
         * 签名
         * */
        private String sign;

    }


}
