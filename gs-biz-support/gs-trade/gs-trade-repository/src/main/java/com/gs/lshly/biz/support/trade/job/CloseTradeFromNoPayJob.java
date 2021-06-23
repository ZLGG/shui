package com.gs.lshly.biz.support.trade.job;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.middleware.redis.RedisUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月18日 下午3:00:00
 */
public class CloseTradeFromNoPayJob implements Job {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String PAYING_TRADE = "trade_paying";
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private TradeMapper tradeMapper;
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext.getJobDetail());
        System.out.println("我是业务，调用时间：" + sdf.format(new Date()));
        Map<Object,Object> map = redisUtil.hmget(PAYING_TRADE);
        if(map!=null){
        	Iterator<Map.Entry<Object,Object>> it= map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Object,Object> entry=it.next();
                String date = entry.getValue().toString();
                Date endDate = DateUtils.parseDate(DateUtils.timeFormatStr, date);
                if(endDate.compareTo(new Date())<=0){
                	String tradeId = entry.getKey().toString();
                	
                	//更新状态
                    Trade trade = tradeMapper.selectById(tradeId);
                    trade.setTradeState(TradeStateEnum.已取消.getCode());
                    tradeMapper.updateById(trade);
                	
                	map.remove(tradeId);
                }
            }
        }
        
        redisUtil.hmset(PAYING_TRADE, map);
    }
}
