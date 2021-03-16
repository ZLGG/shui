package com.gs.lshly.facade.platform.timer;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeSettlementRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Configuration
@Slf4j
public class SettlemenetTimer {

    @DubboReference
    private ITradeSettlementRpc iTradeSettlementRpc;

    /**
     * 每5分钟触发
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void SettlementCollect() {
//        try{
//            //此处将订单表数据根据商家汇总到结算表
//            Boolean result=iTradeSettlementRpc.summaryOrderData();
//            if(result){
//                log.info("结算数据汇总成功");
//            }else{
//                log.error("结算数据汇总失败");
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(), e);
//        }
    }
}
