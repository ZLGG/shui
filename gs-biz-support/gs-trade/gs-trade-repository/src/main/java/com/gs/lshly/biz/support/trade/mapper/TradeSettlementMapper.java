package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zst
 * @since 2020-11-30
 */
@Repository
public interface TradeSettlementMapper extends BaseMapper<TradeSettlement> {

    @Select(
            "SELECT  ts.shop_id,ts.trade_code,ts.goods_amount,ts.discount_amount,ts.delivery_amount,ts.trade_amount\n" +
                    "from gs_trade ts where ts.flag = 0 \n" +
                    "and ts.shop_id = '${shopId}' \n" +
                    "and  ts.cdate BETWEEN  '${billStartTime}' and '${billEndTime}' "
    )
    List<Map<String,Object>> listTrade(@Param("shopId") String shopId, @Param("billStartTime")LocalDateTime billStartTime, @Param("billEndTime")LocalDateTime billEndTime);

    @Select(
            "SELECT \n" +
                    "gtc.trade_amount \n" +
                    "from gs_trade_cancel gtc where gtc.flag = 0 \n" +
                    "and gtc.shop_id = '${shopId}' \n" +
                    "and  gtc.cdate BETWEEN  '${billStartTime}' and '${billEndTime}' "
    )
    List<Map<String,Object>> listTradeCancel(@Param("shopId") String shopId, @Param("billStartTime")LocalDateTime billStartTime, @Param("billEndTime")LocalDateTime billEndTime);

    @Select("SELECT t.`trade_state`,COUNT(t.`id`) " +
            "trade_count FROM gs_trade t  " +
            "WHERE t.`flag`=0  AND ${ew.sqlSegment}")
    List<BbcTradeListVO.stateCountVO> selectTradeStateCount(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO> qw);

    @Select("select gs.id,gs.shop_name from gs_shop gs,gs_merchant gm where gs.merchant_id = gm.id and gs.flag=0 and gm.flag=0")
    List<Map<String,String>> queryEffectiveShop();

    @Select("select IFNULL(sum(goods_amount), 0) sumGoodsAmount,IFNULL(sum(delivery_amount), 0) sumDeliveryAmount,IFNULL(sum(trade_amount), 0) sumTradeAmount,count(*) count " +
            "from gs_trade where flag='0' and trade_state=40 and shop_id=${shopId} and recv_time >= '${startDate}' and recv_time < '${endDate}'")
    Map<String, Object> queryTabulateData(@Param("shopId") String shopId, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("SELECT IFNULL(sum(refund_amount), 0) sumDiscountAmount FROM gs_trade_rights WHERE state=70 and shop_id=${shopId} and complete_time >= '${startDate}' and complete_time < '${endDate}'")
    BigDecimal querySumDiscountAmount(@Param("shopId") String shopId, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("select IFNULL(sum(refund_amount), 0) sumDiscountAmount from gs_trade_rights_goods where flag='0' and shop_id=${shopId} and trade_id=${tradeId} and trade_goods_id=${goodsId}")
    BigDecimal queryGoodsRefundAmount(@Param("shopId") String shopId,@Param("tradeId") String tradeId,@Param("goodsId") String goodsId);

    @Select("select gtr.* from gs_trade gt,gs_trade_rights gtr where gt.id=gtr.trade_id and gt.shop_id=${shopId} and gtr.state=70 and gtr.complete_time >= '${startDate}' and gtr.complete_time  < '${endDate}' and gt.recv_time  < '${startDate}'")
    List<TradeRights> queryRightsData(@Param("shopId") String shopId, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount,IFNULL(sum(quantity), 0) quantity,count(*) count " +
            "FROM gs_trade_settlement WHERE flag='0' and cdate >= '${startDate}' and cdate < '${endDate}'")
    Map<String, Object> queryStatementData(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmountquantity FROM gs_trade WHERE flag='0' AND ${ew.sqlSegment}")
    BigDecimal settlementAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(*) count FROM gs_trade WHERE flag='0' AND ${ew.sqlSegment}")
    Integer orderQuantity(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount,IFNULL(sum(quantity), 0) quantity,count(*) count " +
            "FROM gs_trade_settlement WHERE flag='0' and cdate >= '${startDate}' and cdate < '${endDate}'")
    List<TradeSettlementVO.SaleslVO> pageVo( @Param(Constants.WRAPPER) QueryWrapper<TradeSettlement> qw);

    @Select("SELECT DATE_FORMAT(cdate,'%Y-%m-%d') tradeNum ,IFNULL(sum(trade_amount), 0) tradeAmount, IFNULL(sum(quantity), 0) quantity ,count(*) count " +
            "FROM gs_trade_settlement WHERE flag='0' and cdate >= '${startDate}' and cdate < '${endDate}' GROUP BY DATE_FORMAT(cdate,'%Y-%m-%d') " +
            "ORDER BY DATE_FORMAT(cdate,'%Y-%m-%d')")
    Map<String, Object> quantityqueryStatementTradeNumData(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("SELECT DATE_FORMAT(cdate,'%Y-%m-%d') tradeNum ,IFNULL(sum(trade_amount), 0) tradeAmount, IFNULL(sum(quantity), 0) quantity ,count(*) count " +
            "FROM gs_trade_settlement WHERE flag='0' and cdate >= '${startDate}' and cdate < '${endDate}' GROUP BY DATE_FORMAT(cdate,'%Y-%m-%d') " +
            "ORDER BY DATE_FORMAT(cdate,'%Y-%m-%d')")
    List<TradeSettlementVO.SaleslVO> quantityqueryStatementTradeNumData2(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("SELECT DATE_FORMAT(cdate,'%Y-%m-%d') tradeNum ,IFNULL(sum(trade_amount), 0) tradeAmount, IFNULL(sum(quantity), 0) quantity ,count(*) count " +
            "FROM gs_trade_settlement WHERE flag='0' and cdate >= '${startDate}' and cdate < '${endDate}' " +
            "GROUP BY DATE_FORMAT(cdate,'%Y-%m-%d') ORDER BY DATE_FORMAT(cdate,'%Y-%m-%d')")
    List<TradeSettlement> quantityqueryStatementTradeNumList(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count  from gs_trade  \n" +
            " where flag= 0  AND ${ew.sqlSegment} " )
    List<TradeSettlementVO.PackDateList> packDateList(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);
}
