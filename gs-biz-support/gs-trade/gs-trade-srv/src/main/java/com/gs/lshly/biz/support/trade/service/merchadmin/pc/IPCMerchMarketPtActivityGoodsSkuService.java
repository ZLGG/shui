package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSkuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSkuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSkuVO;

import java.util.List;

public interface IPCMerchMarketPtActivityGoodsSkuService {

    PageData<PCMerchMarketPtActivityGoodsSkuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSkuQTO.QTO qto);

    void addMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto);

    void deleteMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto);


    void editMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto);

    PCMerchMarketPtActivityGoodsSkuVO.DetailVO detailMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto);

    CommonMarketDTO.MarketSku activePtActivitySku(List<CommonMarketDTO.SkuId> skuIds, ActivityTerminalEnum terminal);
}