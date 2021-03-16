package com.gs.lshly.common.struct.pos.body;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Data
@Accessors(chain = true)
public class OOnlineOrderLine extends RSEntity{
    /** 商品 */
    private RSThinSku2 sku;
    /** 数量 */
    private int qty;
    /** 价格 */
    private BigDecimal price = BigDecimal.ZERO;
    /** 小计 */
    private BigDecimal amount = BigDecimal.ZERO;
}
