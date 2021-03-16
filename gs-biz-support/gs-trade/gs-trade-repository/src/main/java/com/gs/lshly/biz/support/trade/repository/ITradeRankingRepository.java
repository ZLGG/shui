package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeRanking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zst
 * @since 2020-12-18
 */
public interface ITradeRankingRepository extends IService<TradeRanking> {


    List<TradeRankingVO.RankingVO> getRankingInfo (LocalDateTime startTime,LocalDateTime startEnd);

    List<Map<String, Object>> getRankingInfo2 (LocalDateTime startTime, LocalDateTime startEnd);
}
