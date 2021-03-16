package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5MarketMerchantCardUsersService;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketMerchantCardUsersService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5MarketMerchantCardUsersRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2021-01-08
*/
@DubboService
public class BbbH5MarketMerchantCardUsersRpc implements IBbbH5MarketMerchantCardUsersRpc {
    @Autowired
    private IBbbH5MarketMerchantCardUsersService iBbbH5MarketMerchantCardUsersService;

    @Override
    public PageData<BbbH5MarketMerchantCardUsersVO.PageData> pageData(BbbH5MarketMerchantCardUsersQTO.QTO qto){
        return iBbbH5MarketMerchantCardUsersService.pageData(qto);
    }
}