package com.gs.lshly.rpc.api.bbb.h5.user;

import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;

public interface IBbbH5UserIntegralRpc {
    void addUserTradeIntergral(BbbH5UserIntegralDTO.ETO eto);
}
