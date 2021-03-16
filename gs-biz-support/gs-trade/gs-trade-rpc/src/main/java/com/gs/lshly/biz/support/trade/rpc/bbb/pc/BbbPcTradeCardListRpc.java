package com.gs.lshly.biz.support.trade.rpc.bbb.pc;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbPcTradeCardListService;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantCardDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbMarketMerchantCardVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeCardListRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class BbbPcTradeCardListRpc implements IBbbPcTradeCardListRpc {
    @Autowired
    private IBbbPcTradeCardListService iBbbPcTradeCardListService;
    @Override
    public List<BbbMarketMerchantCardVO.ListVO> innerCardList(BbbMarketMerchantCardDTO.IdsDTO dto) {
        return iBbbPcTradeCardListService.innerCardList(dto);
    }
}
