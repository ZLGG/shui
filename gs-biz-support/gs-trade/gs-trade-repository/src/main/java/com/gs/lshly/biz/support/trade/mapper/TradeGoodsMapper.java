package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-10-30
 */
@Repository
public interface TradeGoodsMapper extends BaseMapper<TradeGoods> {
    @Select("select t.goods_id goods_id,t.goods_name goods_name from gs_trade_goods t\n" +
            "LEFT JOIN gs_trade tg ON t.trade_id=tg.id \n" +
            "WHERE t.`flag`=0 AND tg.`flag`=0 AND ${ew.sqlSegment} \n" +
            "GROUP BY t.goods_id,t.goods_name;")
    List<PCMerchTradeListVO.innerGoodsIdAndName> selectTradeIng(@Param(Constants.WRAPPER)QueryWrapper<TradeGoods> qw);


    @Select("select goods_id ,goods_name  ,IFNULL(sum(quantity), 0) quantity ,IFNULL(sum(pay_amount), 0) salePrice \n" +
            " from gs_trade_goods \n" +
            " where flag= 0 AND ${ew.sqlSegment} " )
    List<TradeGoodsVO.GoodsSaleVO> goodsSale(@Param(Constants.WRAPPER)QueryWrapper<TradeGoods> qw);


    @Select("select MONTH(cdate) cdate,goods_id ,goods_name  ,IFNULL(sum(quantity), 0) quantity ,IFNULL(sum(pay_amount), 0) payAmount \n" +
            " from gs_trade_goods \n" +
            " where flag= 0 AND ${ew.sqlSegment} " )
    List<TradeGoodsVO.GoodsSaleVO> goodsSaleDetail(@Param(Constants.WRAPPER)QueryWrapper<TradeGoods> qw);

    @Select("SELECT g.goods_id id,g.goods_name,count(g.quantity) goods_sales_volume FROM gs_trade_goods g LEFT JOIN gs_trade t on g.trade_id=t.id   WHERE t.flag=0 AND g.flag=0 AND to_days(g.cdate) = to_days(now())-1   AND ${ew.sqlSegment} GROUP BY g.goods_id,g.goods_name ")
    List<MerchantHomeDashboardVO.GoodsInfo> selectGoodsInfo(@Param(Constants.WRAPPER) QueryWrapper<MerchantHomeDashboardDTO.ETO> qw);

    @Select("SELECT IFNULL(sum(quantity), 0) quantity FROM gs_trade_goods WHERE flag='0'  AND ${ew.sqlSegment}")
    Integer shopQuantity(@Param(Constants.WRAPPER)QueryWrapper<TradeGoods> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(quantity), 0) quantity  from gs_trade_goods  \n" +
            " where flag= 0  AND ${ew.sqlSegment} " )
    List<TradeSettlementVO.PackCountList> packCountList(@Param(Constants.WRAPPER)QueryWrapper<TradeGoods> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade where flag = 0 and trade_state = 40 and DATEDIFF(cdate,NOW())<=0 AND DATEDIFF(cdate,NOW())>-7  AND ${ew.sqlSegment}")
    Integer getWeekDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade where flag = 0 and trade_state = 40 and DATEDIFF(cdate,NOW())<=0 AND DATEDIFF(cdate,NOW())>-30  AND ${ew.sqlSegment}")
    Integer getMonthDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade where flag = 0 and trade_state = 40 and DATEDIFF(cdate,NOW())<=0 AND DATEDIFF(cdate,NOW())>-90  AND ${ew.sqlSegment}")
    Integer getNinetyDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_user_private_user where flag = 0  AND ${ew.sqlSegment} ")
    Integer getUserCountDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select MONTH(cdate) cdate,IFNULL(sum(goods_amount), 0) tradeAmount from gs_trade  where flag = 0 and trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackPerformanceVO> performanceList(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade  where flag = 0 and trade_state = 40 AND ${ew.sqlSegment} " )
    Integer count(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select MONTH(gs.cdate) cdate ,gu.user_name userName,IFNULL(sum(gs.goods_amount), 0) tradeAmount \n" +
            "from gs_trade gs LEFT JOIN gs_user gu ON gs.user_id = gu.id\n" +
            "where gs.flag = 0 and gu.flag = 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackClientListVO> clientList(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select gu.user_name userName,IFNULL(sum(gs.goods_amount), 0) tradeAmount \n" +
            "from gs_trade gs LEFT JOIN gs_user gu ON gs.user_id = gu.id\n" +
            "where gs.flag = 0 and gu.flag = 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackClientVO> clientDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select MONTH(cdate) cdate ,sku_img ,goods_name ,IFNULL(sum(pay_amount), 0) tradeAmount  from gs_trade_goods where flag = 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackGoodsDateVO> packGoodsDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select goods_id goodsId ,goods_name goodsName ,IFNULL(sum(pay_amount), 0) tradeAmount from gs_trade_goods where flag = 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackGoodsDateListVO> packGoodsDateList(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select trade_state tradeState,count(trade_state) count from  gs_trade where flag = 0 and trade_state in(20,10,30,50) AND ${ew.sqlSegment} GROUP BY trade_state" )
    List<TradeVO.packTradeStatesDate> packTradeStatesDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);
}
