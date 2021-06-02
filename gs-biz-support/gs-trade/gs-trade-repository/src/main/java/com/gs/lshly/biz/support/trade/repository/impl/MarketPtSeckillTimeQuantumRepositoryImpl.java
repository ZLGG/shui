package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillTimeQuantum;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillTimeQuantumMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillTimeQuantumRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台秒杀活动 服务实现类
 * </p>
 *
 * @author hanly
 * @since 2021-06-01
 */
@Service
public class MarketPtSeckillTimeQuantumRepositoryImpl extends ServiceImpl<MarketPtSeckillTimeQuantumMapper, MarketPtSeckillTimeQuantum> implements IMarketPtSeckillTimeQuantumRepository {

}
