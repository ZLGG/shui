package com.gs.lshly.biz.support.trade.entity;

    import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    /**
    * <p>
    * 
    * </p>
    *
    * @author oy
    * @since 2020-10-28
    */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("gs_trade")
    public class Trade extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * 交易订单表(ID)
            */
        private String id;


        /**
         * 交易子订单号
         */
        private String childTradeId;

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
             * 发票id
             * */
        private String invoiceId;

        /**
         * 发票地址id
         * */
        private String invoiceAddressId;

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
             * 用户优惠券id
             * */
        private String userCardId;
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
         * 改价原因
         */
        private String changePriceCause;

            /**
            * 创建时间
            */
            @TableField(fill = FieldFill.INSERT)
            private LocalDateTime cdate;

            /**
            * 更新时间
            */
            @TableField(fill = FieldFill.INSERT_UPDATE)
            private LocalDateTime udate;

            /**
            * 逻辑删除标记
            */
            @TableField(fill = FieldFill.INSERT)
            @TableLogic
            private Boolean flag;


}
