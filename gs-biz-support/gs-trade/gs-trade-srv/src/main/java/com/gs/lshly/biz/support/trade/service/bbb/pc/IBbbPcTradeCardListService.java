package com.gs.lshly.biz.support.trade.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantCardDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbMarketMerchantCardVO;

import java.util.List;

public interface IBbbPcTradeCardListService {
    List<BbbMarketMerchantCardVO.ListVO> innerCardList(BbbMarketMerchantCardDTO.IdsDTO dto);
}
