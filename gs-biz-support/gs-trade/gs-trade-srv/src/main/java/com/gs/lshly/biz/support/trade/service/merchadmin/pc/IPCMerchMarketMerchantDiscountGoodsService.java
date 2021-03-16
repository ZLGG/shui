package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountGoodsVO;

import java.util.List;

public interface IPCMerchMarketMerchantDiscountGoodsService {

    PageData<PCMerchMarketMerchantDiscountGoodsVO.ListVO> pageData(PCMerchMarketMerchantDiscountGoodsQTO.QTO qto);

    void addMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto);

    void deleteMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto);


    void editMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto);

    PCMerchMarketMerchantDiscountGoodsVO.DetailVO detailMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto);

    CommonMarketDTO.MarketSku activeDiscountPriceSku(List<CommonMarketDTO.SkuId> goodsInfoVOS, ActivityTerminalEnum terminal);
}