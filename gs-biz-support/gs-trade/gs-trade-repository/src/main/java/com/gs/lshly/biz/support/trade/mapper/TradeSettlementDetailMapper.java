package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeSettlementDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zst
 * @since 2020-12-01
 */
@Repository
public interface TradeSettlementDetailMapper extends BaseMapper<TradeSettlementDetail> {

    @Select("SELECT * FROM gs_trade_settlement_detail WHERE flag='0' AND ${ew.sqlSegment}")
    List<TradeSettlementDetail> listVOS(@Param(Constants.WRAPPER) QueryWrapper<TradeSettlementDetail> qw);
}
