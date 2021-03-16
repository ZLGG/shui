package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeRanking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeComplaintView;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zst
 * @since 2020-12-18
 */
@Repository
public interface TradeRankingMapper extends BaseMapper<TradeRanking> {

    /**
     * 点击量排行
     * @return
     */
    @Select(
            "select gs.goods_name goodsName,count(gsg.id) clickRate from  gs_trade_ranking_log gsg " +
                    "left join gs_trade_ranking gs ON gs.id = gsg.ranking_id  where 1 = 1 " +
                    "and gsg.cdate >= '${startDate}' " +
                    "and gsg.cdate < '${endDate}' " +
                    "GROUP BY gs.goods_name  ORDER BY clickRate desc limit 0,10"
    )
    List<TradeRankingVO.RankingVO> getRankingInfo(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Select(
            "select gs.goods_name goodsName,count(gsg.id) clickRate ,gs.marke_num_spu markeNumSpu,gs.shop_name shopName " +
                    "from  gs_trade_ranking_log gsg " +
                    "left join gs_trade_ranking gs ON gs.id = gsg.ranking_id  " +
                    "where gsg.flag = 0 AND gs.flag = 0 AND ${ew.sqlSegment}"
    )
    IPage<TradeRankingVO.RankingVO> pageVo(IPage<TradeRanking> page, @Param(Constants.WRAPPER) QueryWrapper<TradeRanking> qw);

    @Select(
            "select DISTINCTROW gs.goods_name goodsName ,gs.marke_num_spu markeNumSpu  from  gs_trade_ranking_log gsg " +
                    "left join gs_trade_ranking gs ON gs.id = gsg.ranking_id  where gsg.flag = 0 and gs.flag = 0 " +
                    "and gsg.cdate >= '2020-2-1'" +
                    "and gsg.cdate < '2020-12-25'"
    )
    List<Map<String, Object>> getRankingInfo2(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);
}
