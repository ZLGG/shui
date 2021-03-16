package com.gs.lshly.biz.support.stock.mapper;

import com.gs.lshly.biz.support.stock.entity.StockLogisticsWatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 物流追踪配置 Mapper 接口
 * </p>
 *
 * @author zzg
 * @since 2020-10-24
 */
public interface StockLogisticsWatchMapper extends BaseMapper<StockLogisticsWatch> {

    /*@Select("select a.id, a.param_key,a.param_value,a.state  from  gs_stock_logistics_watch a")
    StockLogisticsWatch   listWatch();

    @Select("select a.id, a.param_key,a.param_value,a.state  from  gs_stock_logistics_watch a where a.param_key = #{paramKey}")
    StockLogisticsWatch  selectOneStock(@Param("paramKey")String paramKey);*/




}
