package com.gs.lshly.biz.support.trade.service.bbc;


import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeGoodsDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;

import java.util.List;
import java.util.Set;

/**
 * 订单营销服务
 * @author lxus
 */
public interface IBbcMarketSettleService {

    void settlement(BbcTradeSettlementVO.ListVO settlementVO, BbcTradeBuildDTO.cartIdsDTO dto);

    void settlement(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbcTradeBuildDTO.DTO dto);

    void settlementPCBBB(BbbTradeSettlementVO.ListVO goodsInfoVOS, BbbTradeBuildDTO.cartIdsDTO dto);

    void settlementPCBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbTradeBuildDTO.DTO dto);

    void settlementH5BBB(BbbH5TradeSettlementVO.ListVO settlementVO, BbbH5TradeBuildDTO.cartIdsDTO dto);

    void settlementH5BBB(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbH5TradeBuildDTO.DTO dto);
}
