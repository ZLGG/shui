package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract  class PosTradeRequestDTO {

    @Data
    @ApiModel("PosTradeRequestDTO.BaseQTO")
    @Accessors(chain = true)
    public static class DTO extends BaseQTO {
        /**
         * 交易订单表(ID)
         */
        private String id;

        /**
         * 会员ID
         */
        private String userId;

        /**
         * 店铺ID
         */
        private String shopId;

        /**
         * 商家ID
         */
        private String merchantId;

        /**
         * 来源类型:10:2C,20:2B,30:POS
         */
        private Integer sourceType;

        /**
         * 交易编号
         */
        private String tradeCode;

        /**
         * 交易状态
         */
        private Integer tradeState;

        /**
         * 商品总金额
         */
        private BigDecimal goodsAmount;

        /**
         * 需要发票
         * */
        private Boolean isInvoice;

        /**
         * 优惠金额
         */
        private BigDecimal discountAmount;

        /**
         * 运费金额
         */
        private BigDecimal deliveryAmount;

        /**
         * 交易总金额
         */
        private BigDecimal tradeAmount;

        /**
         * 商家优惠金额
         * */
        private BigDecimal merchantAmount;
        /**
         * 创建时间
         */
        private LocalDateTime createTime;

        /**
         * 支付时间
         */
        private LocalDateTime payTime;

        /**
         * 收货时间
         */
        private LocalDateTime recvTime;

        /**
         * 支付类型
         */
        private Integer payType;

        /**
         * 配送类型
         */
        private Integer deliveryType;

        /**
         * 自提码
         */
        private String takeGoodsCode;

        /**
         * 自提码图片
         */
        private String takeGoodsQrcode;

        /**
         * 收货地址ID
         */
        private String recvAddresId;

        /**
         * 收货人
         */
        private String recvPersonName;

        /**
         * 收货人电话
         */
        private String recvPhone;

        /**
         * 收货地址全文本
         */
        private String recvFullAddres;

        /**
         * 是否超时取消
         */
        private Integer timeoutCancel;

        /**
         * 买家留言
         */
        private String buyerRemark;

        /**
         * 发货备注
         */
        private String deliveryRemark;

        /**
         * 是否隐藏订单:1:是
         */
        private Integer isHide;

        /**
         * 创建时间
         */
        private LocalDateTime cdate;

        /**
         * 更新时间
         */

        private LocalDateTime udate;

        /**
         * 逻辑删除标记
         */

        private Boolean flag;


    }

}
