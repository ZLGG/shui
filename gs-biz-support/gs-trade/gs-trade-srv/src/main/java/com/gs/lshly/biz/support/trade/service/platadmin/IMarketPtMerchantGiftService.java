package com.gs.lshly.biz.support.trade.service.platadmin;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;

public interface IMarketPtMerchantGiftService {

    PageData<PCMerchMarketMerchantGiftVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto);

    PCMerchMarketMerchantGiftVO.PlatformCutView get(PCMerchMarketMerchantGiftDTO.IdDTO id);

    void check(PCMerchMarketMerchantGiftDTO.Check dto);
}
