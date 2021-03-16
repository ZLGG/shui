package com.gs.lshly.biz.support.trade.mapper.view;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 16:18 2020/12/26
 */
@Data
public class TradeInfoView implements Serializable {

    private String tradeId;

    private String tradeCode;

    private Integer tradeState;

    private String shopId;

    private LocalDateTime cdate;

    private LocalDateTime deliveryTime;

    private String goodsNo;

    private String goodsName;

    private Integer quantity;

    private BigDecimal salePrice;

    private BigDecimal payAmount;

    private BigDecimal merchantAmount;
}
