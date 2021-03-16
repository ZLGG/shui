package com.gs.lshly.biz.support.user.rpc.bbb.pc;

import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserIntegralService;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserIntegralRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BbbUserIntegralRpc implements IBbbUserIntegralRpc {
    @Autowired
    private IBbbUserIntegralService iBbbUserIntegralService;

    @Override
    public void addUserTradeIntergral(PCBbbUserIntegralDTO.ETO eto) {
        iBbbUserIntegralService.addUserTradeIntergral(eto);
    }
}
