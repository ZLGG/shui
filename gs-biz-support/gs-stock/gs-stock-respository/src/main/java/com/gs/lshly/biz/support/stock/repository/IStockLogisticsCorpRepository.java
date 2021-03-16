package com.gs.lshly.biz.support.stock.repository;

import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 物流公司总表 服务类
 * </p>
 *
 * @author zhaozhiagng
 * @since 2020-10-22
 */
public interface IStockLogisticsCorpRepository extends IService<StockLogisticsCorp> {

    /*@Select("select gslc.name, gsslc.state from  gs_stock_logistics_corp  gslc join gs_stock_shop_logistics_corp gsslc  on  gslc.id = gsslc.logistics_corp_id")
    void selectList();*/
}
