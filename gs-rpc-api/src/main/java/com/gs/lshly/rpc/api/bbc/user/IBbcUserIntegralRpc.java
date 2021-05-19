package com.gs.lshly.rpc.api.bbc.user;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

public interface IBbcUserIntegralRpc {
	
    void addUserTradeIntergral(BbcUserIntegralDTO.ETO eto);


    BbcUserVO.UserIntegralVO integral(BaseDTO dto);
    
}
