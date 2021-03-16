package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutGoodsVO;

import java.util.List;

public interface IPCMerchMarketMerchantCutGoodsService {

    PageData<PCMerchMarketMerchantCutGoodsVO.ListVO> pageData(PCMerchMarketMerchantCutGoodsQTO.QTO qto);

    void addMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto);

    void deleteMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto);


    void editMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto);

    PCMerchMarketMerchantCutGoodsVO.DetailVO detailMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto);

    CommonMarketDTO.MarketSku activeCutPriceSku(List<CommonMarketDTO.SkuId> goodsInfoVOS, ActivityTerminalEnum terminal);
}