package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeRanking;
import com.gs.lshly.biz.support.trade.mapper.TradeRankingMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeRankingRepository;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zst
 * @since 2020-12-18
*/
@Service
public class TradeRankingRepositoryImpl extends ServiceImpl<TradeRankingMapper, TradeRanking> implements ITradeRankingRepository {

    @Autowired
    private TradeRankingMapper tradeRankingMapper;

    @Override
    public List<TradeRankingVO.RankingVO> getRankingInfo(LocalDateTime startTime, LocalDateTime endTime) {
        return tradeRankingMapper.getRankingInfo(startTime,endTime);
    }

    @Override
    public List<Map<String, Object>> getRankingInfo2(LocalDateTime startTime, LocalDateTime endTime) {
        return tradeRankingMapper.getRankingInfo2(startTime,endTime);
    }
}