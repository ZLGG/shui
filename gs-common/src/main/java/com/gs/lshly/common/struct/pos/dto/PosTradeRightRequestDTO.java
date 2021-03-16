package com.gs.lshly.common.struct.pos.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


public abstract class PosTradeRightRequestDTO {

    @Data
    @ApiModel("PosTradeRightRequestDTO.DTO")
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
         * 订单编号
         * */
        private String posOrderNo;
        /**
         * 订单退货申请单号
         * */
        private String posOrderRefundNo;
        /**
         * POS店处理意见
         * */
        private String posOpinion;
        /**
         * POS店处理结果[10-同意退货，20-拒绝退货，30-直接退款/收到退货]
         * */
        private Integer posProcessResult;
        /**
         * 签名
         * */
        private String sign;

    }


}
