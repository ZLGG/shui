package com.gs.lshly.biz.support.stock.mapper;

import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物流公司总表 Mapper 接口
 * </p>
 *
 * @author zst
 * @since 2020-3-17
 */
@Repository
public interface StockLogisticsCorpMapper extends BaseMapper<StockLogisticsCorp> {

    @Select("delete from gs_stock_logistics_corp")
    void deleteCorp();


}
