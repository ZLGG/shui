package com.gs.lshly.biz.support.trade.rpc.fy;

import com.gs.lshly.biz.support.trade.service.fy.IFyPayService;
import com.gs.lshly.rpc.api.fy.IFyPayRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class FyPayRpc implements IFyPayRpc {
    @Autowired
    private IFyPayService iFyPayService;
    @Override
    public void payEditState(String tradeId) {
         iFyPayService.payEditState(tradeId);
    }
}
