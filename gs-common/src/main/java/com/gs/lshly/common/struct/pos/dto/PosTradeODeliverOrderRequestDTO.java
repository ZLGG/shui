package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.pos.body.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public abstract class PosTradeODeliverOrderRequestDTO {

    @Data
    @ApiModel("PosTradeODeliverOrderRequestDTO.DTO")
    @Accessors(chain = true)
    public static class DTO  implements Serializable {

        // 门店id 不允许为空
        private String shop;
        // 平台id，不允许为空
        private String platformId;
        /** 运费 */
        private BigDecimal freight = BigDecimal.ZERO;
        /** 收货人信息 */
        private OReceiverInfo receiverInfo;
        /** 订单类型 ShopDeliverOrder门店配送 PickUpInStoreOrder 自提订单 ExpressDeliverOrder快递订单 */
        private String orderType;
        /** 自提码 */
        private String pickUpCode;
        /** 订单号 */
        private String number;
        /** 状态 ORDERED("已下单"), FINISHED("已完成"), CANCELED("已取消") */
        private String state;
        /** 下单时间 */
        private Date orderTime;
        /** 顾客 */
        private OCustomer customer;
        /** 商品总数 */
        private int qty;
        /** 合计。订单行金额之和 */
        private BigDecimal amount = BigDecimal.ZERO;
        /** 优惠金额。在合计的基础上优惠的金额 */
        private BigDecimal discountAmount = BigDecimal.ZERO;
        /** 商品行 */
        private List<OOnlineOrderLine> lines;
        /** 支付方式列表 */
        private List<OOnlineOrderPayment> payments;
        /** 顾客备注 */
        private String customerRemark;
        /** 备注 */
        private String remark;
    }


}
