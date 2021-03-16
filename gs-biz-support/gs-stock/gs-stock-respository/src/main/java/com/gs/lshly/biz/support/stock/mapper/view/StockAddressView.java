package com.gs.lshly.biz.support.stock.mapper.view;

import com.gs.lshly.biz.support.stock.entity.StockAddress;
import lombok.Data;

@Data
public class StockAddressView extends StockAddress {

    /**
     * 是否默认[0=否 1=是]
     */
    private Integer  isDefault;

    /**
     * 是否启用[0=否 1=是]
     */
    private Integer  state;

}
