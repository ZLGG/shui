package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeComplaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeComplaintView;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-12-23
 */
@Repository
public interface TradeComplaintMapper extends BaseMapper<TradeComplaint> {

    @Select("SELECT DISTINCT tc.*,gt.trade_code,tg.goods_name FROM gs_trade_complaint tc \n" +
            "left JOIN gs_trade gt ON tc.trade_id = gt.id\n" +
            "LEFT JOIN gs_trade_goods tg ON tc.trade_goods_id = tg.id\n" +
            "WHERE tc.flag = 0 AND ${ew.sqlSegment}")
    IPage<TradeComplaintView> pageVo(IPage<TradeComplaintView> page, @Param(Constants.WRAPPER) QueryWrapper<TradeComplaintView> qw);


    @Select("SELECT DISTINCT tc.*,gt.trade_code,tg.goods_name FROM gs_trade_complaint tc \n" +
            "left JOIN gs_trade gt ON tc.trade_id = gt.id\n" +
            "LEFT JOIN gs_trade_goods tg ON tc.trade_goods_id = tg.id\n" +
            "WHERE tc.flag = 0 AND ${ew.sqlSegment}")
    TradeComplaintView getDetail(@Param(Constants.WRAPPER) QueryWrapper<TradeComplaintView> qw);
}
