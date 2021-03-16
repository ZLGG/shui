package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.mapper.TradePayMapper;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-11-04
*/
@Service
public class TradePayRepositoryImpl extends ServiceImpl<TradePayMapper, TradePay> implements ITradePayRepository {

}