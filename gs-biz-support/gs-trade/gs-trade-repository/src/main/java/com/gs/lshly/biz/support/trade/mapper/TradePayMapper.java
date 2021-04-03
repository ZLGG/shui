package com.gs.lshly.biz.support.trade.mapper;

import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-11-04
 */
public interface TradePayMapper extends BaseMapper<TradePay> {

    /**
     * 根据ids逻辑删除
     *
     * @param id
     * @return
     */
    @Update("update gs_trade_pay set flag = 1 " +
            "where id = #{id}")
    Integer delById(@Param("id") String id);

}
