package com.gs.lshly.biz.support.stock.mapper.view;

import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import lombok.Data;

@Data
public class StockShopLogisticsCorpView extends StockLogisticsCorp {

   private String shop_id;

   private String merchant_id;

}
