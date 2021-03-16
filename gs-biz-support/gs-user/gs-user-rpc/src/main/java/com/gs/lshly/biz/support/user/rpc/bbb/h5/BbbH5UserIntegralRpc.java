package com.gs.lshly.biz.support.user.rpc.bbb.h5;

import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserIntegralService;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserIntegralService;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserIntegralRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserIntegralRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BbbH5UserIntegralRpc implements IBbbH5UserIntegralRpc {
    @Autowired
    private IBbbH5UserIntegralService iBbbUserIntegralService;

    @Override
    public void addUserTradeIntergral(BbbH5UserIntegralDTO.ETO eto) {
        iBbbUserIntegralService.addUserTradeIntergral(eto);
    }
}
