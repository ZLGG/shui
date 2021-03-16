package com.gs.lshly.common.enums.stock;

/**
 * 运费计算方式
 * @author lxus
 * @since 2020-11-18
 */
public enum StockDeliveryCostCalcWayEnum {

    /**
     * 对应卖家承担时为0，或者店铺配送时的固定配送费用
     */
    固定运费,
    按重量,
    按件数,
    按金额

}
