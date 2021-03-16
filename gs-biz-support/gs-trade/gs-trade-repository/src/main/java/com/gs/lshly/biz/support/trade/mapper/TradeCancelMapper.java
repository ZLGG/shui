package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 交易订单取消表 Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-11-20
 */
public interface TradeCancelMapper extends BaseMapper<TradeCancel> {

    @Select("SELECT td.*,t.`pay_type` as `pay_type` " +
            "FROM `gs_trade_cancel` td " +
            "LEFT JOIN `gs_trade` t ON t.id=td.`trade_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<TradeCancelVO.ListVO> selectListPage(IPage<TradeCancelVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeCancelQTO.QTO> qw);

    @Select("SELECT td.* " +
            "FROM `gs_trade_cancel` td " +
            "LEFT JOIN `gs_trade` t ON t.id=td.`trade_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<TradeCancel> selectCancelListPage(IPage<TradeCancel> page,@Param(Constants.WRAPPER)  QueryWrapper<TradeCancel> qw);
}
