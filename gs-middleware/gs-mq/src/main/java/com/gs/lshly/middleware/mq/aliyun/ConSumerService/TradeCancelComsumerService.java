package com.gs.lshly.middleware.mq.aliyun.ConSumerService;

public interface TradeCancelComsumerService {
    void TradeTimeOutCancel(String tradeId);
}
