package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsWatch;
import com.gs.lshly.biz.support.stock.mapper.view.StockLogView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 库存管理 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-11-02
 */
@Repository
public interface StockLogMapper extends BaseMapper<StockLog> {


    @Select("SELECT goods_id,SUM(quantity_change) sales_volume FROM  gs_stock_log " +
            "WHERE flag = 0  and ${ew.sqlSegment}")
    List<StockLogView>  sumSalesVolume(@Param(value = "ew") QueryWrapper<StockLog> ew);
}
