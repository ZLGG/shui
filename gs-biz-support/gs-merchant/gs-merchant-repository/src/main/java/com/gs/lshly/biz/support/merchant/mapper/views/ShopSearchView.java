package com.gs.lshly.biz.support.merchant.mapper.views;

import com.gs.lshly.biz.support.merchant.entity.Shop;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopSearchView extends Shop {

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 销量
     */
    private Integer sales;

}
