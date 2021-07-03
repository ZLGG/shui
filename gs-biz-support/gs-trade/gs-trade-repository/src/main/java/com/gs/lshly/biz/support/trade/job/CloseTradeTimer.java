package com.gs.lshly.biz.support.trade.job;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSONObject;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.middleware.redis.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class CloseTradeTimer {

	private final String PAYING_TRADE = "trade_paying";

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private TradeMapper tradeMapper;

	/**
	 * 关闭交易订单
	 */
	@Scheduled(cron = "*/1 * * * * ?")
	public void SettlementCollect() {
		JSONObject retJson = new JSONObject();
		JSONObject jsonInfo = new JSONObject();
    	if(redisUtil.get(PAYING_TRADE)!=null){
    		String data = (String) redisUtil.get(PAYING_TRADE);
    		jsonInfo = JSONObject.parseObject(data);
    		
    		if (jsonInfo != null) {
    			Iterator iter = jsonInfo.entrySet().iterator();
    			while (iter.hasNext()) {
    				Map.Entry entry = (Map.Entry) iter.next();
    				String tradeId = entry.getKey().toString();
    				String date = entry.getValue().toString();
    				Date endDate = DateUtils.parseDate(DateUtils.timeFormatStr, date);
    				if (endDate.compareTo(new Date()) <= 0) {
    					// 更新状态
    					Trade trade = tradeMapper.selectById(tradeId);
    					if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
    						trade.setTradeState(TradeStateEnum.已取消.getCode());
    						tradeMapper.updateById(trade);
    					}
    				} else {
    					retJson.put(tradeId, date);
    				}
    			}

    		}
    	}
		
		redisUtil.set(PAYING_TRADE, retJson.toString());

	}
}
