package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.mapper.view.TradeInfoView;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-10-28
 */
@Repository
public interface TradeMapper extends BaseMapper<Trade> {

    @Select("SELECT t.*,count(tg.id) as bc,count(tc.id) as cc FROM gs_trade AS t LEFT JOIN gs_trade_comment AS tc ON t.id = tc.trade_id LEFT JOIN gs_trade_goods AS tg ON tg.trade_id = t.id where t.`flag`=0 AND ${ew.sqlSegment}")
    List<BbcTradeListVO.tradeVO> selectTradeListPage( @Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO.TradeList> qw);


    @Select(" SELECT t.`trade_state`,COUNT(t.`id`) " +
            "trade_count FROM gs_trade t  " +
            "WHERE t.`flag`=0  AND ${ew.sqlSegment}")
    List<BbcTradeListVO.stateCountVO> selectTradeStateCount(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO> qw);

    @Select("select t.*" +
            "from gs_trade t," +
            "gs_trade_goods t2," +
            "gs_goods_info t3 " +
            "WHERE t.`id` = t2.trade_id " +
            "and t3.id = t2.goods_id " +
            "and t.`flag`=0 " +
            "AND ${ew.sqlSegment}")
    IPage<PCMerchTradeListVO.tradeVO> selectPCMerchTradePage(IPage<PCMerchTradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeQTO.TradeList> qw);

    @Select("select t.*" +
            "from gs_trade t," +
            "gs_trade_goods t2," +
            "gs_goods_info t3, " +
            "gs_user t4 "+
            "WHERE t.`id` = t2.trade_id " +
            "and t3.id = t2.goods_id " +
            "and t4.id = t.user_id "+
            "and t.`flag`=0 " +
            "AND ${ew.sqlSegment}")
    List<Trade> selectPCMerchTrade(@Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeQTO.IdListQTO> qw);

    @Select("SELECT t.*,tg.`id` trade_goods_id,tg.`pay_amount` " +
            "FROM `gs_trade` t " +
            "LEFT JOIN  `gs_trade_goods` tg ON t.`id`=tg.`trade_id`" +
            "WHERE t.`flag`=0 AND tg.`flag`=0 AND ${ew.sqlSegment}")
    IPage<TradeListVO.tradeVO> selectTradePage(IPage<TradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeQTO.TradeList> qw);

    @Select("SELECT t.*,tg.`id` trade_goods_id,tg.`pay_amount` " +
            "FROM `gs_trade` t " +
            "LEFT JOIN  `gs_trade_goods` tg ON t.`id`=tg.`trade_id`" +
            "WHERE t.`flag`=0 AND tg.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbbTradeListVO.tradeVO> selectBbbTradeListPage(IPage<BbbTradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbOrderQTO.TradeList> qw);


    @Select("SELECT DISTINCT\n" +
            "  gt.id,gt.merchant_amount,\n" +
            "\tgt.trade_code,\n" +
            "\tgt.trade_state,\n" +
            "\tgt.shop_id,\n" +
            "\tgt.cdate,\n" +
            "\tgtd.delivery_time,\n" +
            "\tgtg.goods_no,\n" +
            "\tgtg.goods_name,\n" +
            "\tgtg.quantity,\n" +
            "\tgtg.sale_price,\n" +
            "\tgtg.pay_amount\n" +
            "FROM\n" +
            "\tgs_trade gt\n" +
            "\tLEFT JOIN gs_trade_delivery gtd ON gt.id = gtd.trade_id\n" +
            "\tLEFT JOIN gs_trade_goods gtg ON gt.id = gtg.trade_id\n" +
            "WHERE gt.id = #{tradeId}")
    TradeInfoView getTradeInfo(@Param("tradeId") String tradeId);
    @Select("SELECT t.*,count(tg.id) as bc,count(tc.id) as cc FROM gs_trade AS t LEFT JOIN gs_trade_comment AS tc ON t.id = tc.trade_id LEFT JOIN gs_trade_goods AS tg ON tg.trade_id = t.id where t.`flag`=0 AND ${ew.sqlSegment}")
    List<BbbH5TradeListVO.tradeVO>  BbbH5selectTradeListPage(@Param(Constants.WRAPPER)  QueryWrapper<BbbH5TradeQTO.TradeList> qw);
    @Select(" SELECT t.`trade_state`,COUNT(t.`id`) " +
            " trade_count FROM gs_trade t  " +
            " WHERE t.`flag`=0  AND ${ew.sqlSegment}")
    List<BbbH5TradeListVO.stateCountVO> BbbH5selectTradeStateCount(@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeQTO> qw);
    @Select("SELECT * FROM gs_trade WHERE to_days(cdate) = to_days(now())-1 AND flag=0 AND ${ew.sqlSegment}")
    List<Trade> yesterdayTrade(@Param(Constants.WRAPPER)QueryWrapper<MerchantHomeDashboardDTO.ETO> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-1) AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> payDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade where flag= 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> ayDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-1) AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> packCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade where flag= 0 AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> ackCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-1) AND trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> yesterDayFinishlist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade where flag= 0 AND trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> esterDayFinishlist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-2) AND trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> anteayerFinishlist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> weekFinishlist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate)  AND trade_state = 40 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> monthFinishlist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-2) AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> anteayerPayDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE(cdate) =DATE(CURDATE()-2) AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> anteayerPackCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> weekPayDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade where flag= 0 AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> ayDate(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> weekPackCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> eekPackCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount ,count(1) count \n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND ${ew.sqlSegment} " )
    List<TradeVO.PackDatelistVO> monthPayDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select DATE_FORMAT(cdate,'%Y-%m-%d') cdate,IFNULL(sum(trade_amount), 0) tradeAmount, count(1) count\n" +
            " from gs_trade  \n" +
            " where flag= 0 AND DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND trade_state = 50 AND ${ew.sqlSegment} " )
    List<TradeVO.PackCancelDatelistVO> monthPackCancelDatelist(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount FROM gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND ${ew.sqlSegment}")
    BigDecimal yesterdayAddTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount FROM gs_trade WHERE flag=0 AND ${ew.sqlSegment}")
    BigDecimal esterdayAddTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(1) count FROM gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND ${ew.sqlSegment}")
    Integer yesterdayAddTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(1) count FROM gs_trade WHERE flag=0 AND ${ew.sqlSegment}")
    Integer esterdayAddTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    BigDecimal yesterdayPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    BigDecimal esterdayPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer yesterdayPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer esterdayPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    BigDecimal yesterdayCancelTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    BigDecimal esterdayCancelTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    Integer yesterdayCancelTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    Integer esterdayCancelTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount FROM gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND ${ew.sqlSegment}")
    BigDecimal anteayerAddTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(1) count FROM gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND ${ew.sqlSegment}")
    Integer anteayerAddTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    BigDecimal anteayerPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer anteayerPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    BigDecimal anteayerCancelTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    Integer anteayerCancelTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);


    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount FROM gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND ${ew.sqlSegment}")
    BigDecimal weekAddTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(1) count FROM gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND ${ew.sqlSegment}")
    Integer weekAddTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    BigDecimal weekPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer weekPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    BigDecimal weekCancelTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    Integer weekCancelTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT IFNULL(sum(trade_amount), 0) tradeAmount FROM gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND ${ew.sqlSegment}")
    BigDecimal monthAddTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("SELECT count(1) count FROM gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND ${ew.sqlSegment}")
    Integer monthAddTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    BigDecimal monthPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer monthPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    BigDecimal monthCancelTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state = 50 AND ${ew.sqlSegment}")
    Integer monthCancelTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade_rights WHERE flag=0 AND DATE(cdate) =DATE(CURDATE()-1)  AND state = 99 AND ${ew.sqlSegment}")
    Integer yesterDayAftermarketCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade_rights WHERE flag=0 AND state = 99 AND ${ew.sqlSegment}")
    Integer esterDayAftermarketCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade_rights WHERE flag=0 AND DATE(cdate) =DATE(CURDATE()-2)  AND state = 99 AND ${ew.sqlSegment}")
    Integer anteayerAftermarketCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade_rights WHERE flag=0 AND DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate)  AND state = 99 AND ${ew.sqlSegment}")
    Integer weekAftermarketCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade_rights WHERE flag=0 AND  DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND state = 99 AND ${ew.sqlSegment}")
    Integer monthAftermarketCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state = 40 AND ${ew.sqlSegment}")
    Integer yesterDayPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    Integer yesterDayNotPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE  flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    Integer esterDayNotPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    Integer anteayerNotPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    Integer weekNotPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select count(1) count from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    Integer monthNotPayTradeCount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-1) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    BigDecimal yesterDayNotPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    BigDecimal esterDayNotPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE(cdate) =DATE(CURDATE()-2) AND flag=0 AND trade_state = !40 AND ${ew.sqlSegment}")
    BigDecimal anteayerNotPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 7 DAY) <=DATE(cdate) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    BigDecimal weekNotPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);

    @Select("select IFNULL(sum(trade_amount), 0) tradeAmount from gs_trade WHERE DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(cdate) AND flag=0 AND trade_state != 40 AND ${ew.sqlSegment}")
    BigDecimal monthNotPayTradeAmount(@Param(Constants.WRAPPER)QueryWrapper<Trade> qw);
    @Select("SELECT t.*,tg.`id` trade_goods_id,tg.`pay_amount` " +
            "FROM `gs_trade` t " +
            "LEFT JOIN  `gs_trade_goods` tg ON t.`id`=tg.`trade_id`" +
            "WHERE t.`flag`=0 AND tg.`flag`=0 AND ${ew.sqlSegment}")
    IPage<H5MerchTradeListVO.tradeVO> selectH5MerchTradePage(IPage<H5MerchTradeListVO.tradeVO> page, @Param(Constants.WRAPPER)QueryWrapper<H5MerchTradeQTO.TradeList> qw);

    @Select("SELECT\n" +
            "\tIFNULL(SUM( tg.quantity ),0) saleNum  \n" +
            "FROM\n" +
            "\tgs_trade_goods tg\n" +
            "\tINNER  JOIN gs_trade t ON tg.trade_id = t.id \n" +
            "WHERE\n" +
            "\ttg.goods_id = #{goodsId} \n" +
            "\tAND tg.flag = 0 \n" +
            "\tAND (t.trade_state != 10 and t.trade_state != 50)\n" +
            "\tand DATE_FORMAT(tg.cdate,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m')")
    /**
     * 获取当月的销售数量
     */
    int getCurrentMonthSaleNum(@Param("goodsId") String goodsId);


    @Select("select ifnull(avg(describe_grade),0) describe_grade,ifnull(avg(delivery_grade),0) delivery_grade,ifnull(avg(service_grade),0) service_grade from gs_trade_comment where flag=0  AND ${ew.sqlSegment}")
    BbbTradeListVO.InnerGoodsScore selectShopScore(@Param(Constants.WRAPPER)QueryWrapper<Object> queryWrapper);

    @Select("select ifnull(avg(describe_grade),0) goodsGrade ,COUNT(1) commentCount from gs_trade_comment where flag=0 AND ${ew.sqlSegment}")
    BbbTradeListVO.InnerGoodsScore selectGoodScore(@Param(Constants.WRAPPER) QueryWrapper<Object> queryWrapper);

    //订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消
    @Select("select count(id) from gs_trade where id in (select trade_id from gs_trade_goods where goods_id = #{id} and flag = 0) and trade_state != 10 and trade_state !=50 and flag = 0")
    Integer getSaleQuantity(@Param("id") String id);

    /**
     * 计算总计要付的钱
     * @param
     * @return
     */
    @Select("select IFNULL(sum(trade_point_amount), 0) tradePointAmount \n" +
            " from gs_trade  \n" +
            " where flag= 0 AND id in ('${tradeIds}')" )
    Integer sumTradePointAmount(@Param("tradeIds") String tradeIds);

    @Update("update gs_trade set recv_addres_id = #{recvAddressId}, recv_person_name = #{recvPersonName}, recv_phone = #{recvPhone}, recv_full_addres = #{recvFullAddress}, is_modify_address = 1, udate = now() where id = #{id}")
    void modifyOrderAddress(BbcTradeDTO.ModifyOrderAddressDTO dto);

    @Select("select distinct t2.exchange_type\n" +
            "FROM gs_trade t1,\n" +
            "gs_goods_info t2,\n" +
            "gs_trade_goods t3\n" +
            "WHERE t1.id = t3.trade_id\n" +
            "and t2.id = t3.goods_id\n" +
            "and t1.id = #{tradeId} limit 1")
    Integer getExchangeType(@Param("tradeId")String tradeId);

    @Select("select t1.user_id\n" +
            "FROM gs_trade t1,\n" +
            "gs_goods_info t2,\n" +
            "gs_trade_goods t3\n" +
            "WHERE t1.id = t3.trade_id\n" +
            "and t2.id = t3.goods_id\n" +
            "and t1.id = #{tradeId}")
    String getUserId(@Param("tradeId")String tradeId);
}
