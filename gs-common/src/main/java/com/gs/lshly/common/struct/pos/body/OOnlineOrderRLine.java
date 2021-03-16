package com.gs.lshly.common.struct.pos.body;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
@Data
@Accessors(chain = true)
public class OOnlineOrderRLine extends RSEntity{
    private String orderRId;
    /** 商品 */
    private RSThinSku2 sku;
    /** 原单数量 */
    private int orderQty;
    /** 原单价格 */
    private BigDecimal orderPrice = BigDecimal.ZERO;
    /** 原单小计 */
    private BigDecimal orderAmount = BigDecimal.ZERO;
    /** 退货数量 */
    private int qty;
    /** 退货价格 */
    private BigDecimal price = BigDecimal.ZERO;
    /** 退货金额 */
    private BigDecimal amount = BigDecimal.ZERO;
    /** 更换为的商品 */
    private List<OOnlineOrderReplaceLine> replaceLines;
}
