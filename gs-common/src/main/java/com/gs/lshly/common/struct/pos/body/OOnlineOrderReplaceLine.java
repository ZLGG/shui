package com.gs.lshly.common.struct.pos.body;

import java.math.BigDecimal;

public class OOnlineOrderReplaceLine extends RSEntity {
    /** 更换为的商品 */
    private RSThinSku2 sku;
    /** 数量 */
    private int qty;
    /** 换货价格 */
    private BigDecimal price = BigDecimal.ZERO;
    /** 换货金额 */
    private BigDecimal amount = BigDecimal.ZERO;
    /** 差额。 原单对应数量金额 - 换货金额。商家需退款，金额为正；顾客需付款，金额为负 */
    private BigDecimal differencesAmount = BigDecimal.ZERO;
}