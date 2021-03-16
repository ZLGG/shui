package com.gs.lshly.rpc.api.bbb.pc.trade;

import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantCardDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbMarketMerchantCardVO;

import java.util.List;

public interface IBbbPcTradeCardListRpc {
    List<BbbMarketMerchantCardVO.ListVO> innerCardList(BbbMarketMerchantCardDTO.IdsDTO dto);
}
