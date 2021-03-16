package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyGoodsVO;

import java.util.List;

public interface IPCMerchMarketMerchantGroupbuyGoodsService {

    PageData<PCMerchMarketMerchantGroupbuyGoodsVO.ListVO> pageData(PCMerchMarketMerchantGroupbuyGoodsQTO.QTO qto);

    void addMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto);

    void deleteMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto);


    void editMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto);

    PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO detailMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto);

    CommonMarketDTO.MarketSku activeGroupbuySku(List<CommonMarketDTO.SkuId> skuIds, ActivityTerminalEnum terminal);
}