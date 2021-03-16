package com.gs.lshly.rpc.api.bbb.pc.user;

import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;

public interface IBbbUserIntegralRpc {
    void addUserTradeIntergral(PCBbbUserIntegralDTO.ETO eto);
}
