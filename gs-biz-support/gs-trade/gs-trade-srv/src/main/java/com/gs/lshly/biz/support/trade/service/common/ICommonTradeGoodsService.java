package com.gs.lshly.biz.support.trade.service.common;

import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;

/**
 * @author jdt
 * @date 2021/3/24 10:11
 */
public interface ICommonTradeGoodsService {

    Integer sumQuantity(String goodsId);

}