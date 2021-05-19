package com.gs.lshly.biz.support.user.rpc.bbc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.user.service.bbc.IBbcUserIntegralService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserIntegralRpc;

@DubboService
public class BbcUserIntegralRpc implements IBbcUserIntegralRpc {
    @Autowired
    private IBbcUserIntegralService iBbcUserIntegralService;

    @Override
    public void addUserTradeIntergral(BbcUserIntegralDTO.ETO eto) {
        iBbcUserIntegralService.addUserTradeIntergral(eto);
    }

    @Override
    public BbcUserVO.UserIntegralVO integral(BaseDTO dto) {
        return iBbcUserIntegralService.integral(dto);
    }

}
