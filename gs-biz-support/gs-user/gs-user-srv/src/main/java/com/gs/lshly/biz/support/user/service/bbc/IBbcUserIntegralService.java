package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

public interface IBbcUserIntegralService {
    void addUserTradeIntergral(BbcUserIntegralDTO.ETO eto);

    BbcUserVO.UserIntegralVO integral(BaseDTO dto);
}
