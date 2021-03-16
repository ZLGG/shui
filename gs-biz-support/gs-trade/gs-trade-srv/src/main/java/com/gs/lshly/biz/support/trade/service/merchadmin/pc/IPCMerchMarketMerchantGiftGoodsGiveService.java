package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
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
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsGiveDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsGiveQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsGiveVO;

import java.util.List;

public interface IPCMerchMarketMerchantGiftGoodsGiveService {

    PageData<PCMerchMarketMerchantGiftGoodsGiveVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsGiveQTO.QTO qto);

    void addMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto);

    void deleteMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto);


    void editMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto);

    PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO detailMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto);

    CommonMarketDTO.SkuId activeGiveSku(List<CommonMarketDTO.SkuId> goodsInfoVOS, ActivityTerminalEnum terminal);

    BbcTradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVO(CommonMarketDTO.SkuId giveSkuId);

    BbcTradeGoodsDTO.ETO fillBbcGoodsInfoOrderVO(CommonMarketDTO.SkuId giveSkuId, BbcTradeBuildDTO.DTO dto);

    BbbTradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVOBBB(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto);

    BbbTradeGoodsDTO.ETO fillBbcGoodsInfoOrderVOBBB(CommonMarketDTO.SkuId giveSkuId, BbbTradeBuildDTO.DTO dto);

    BbbH5TradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVOBBBH5(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto);

    BbbH5TradeGoodsDTO.ETO fillBbcGoodsInfoOrderVOBBBH5(CommonMarketDTO.SkuId giveSkuId, BbbH5TradeBuildDTO.DTO dto);
}