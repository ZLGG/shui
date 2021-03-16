package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;

public interface IMarketPtMerchantDiscountService {
    PageData<PCMerchMarketMerchantDiscountVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto);

    PCMerchMarketMerchantDiscountVO.PlatformCutView get( PCMerchMarketMerchantDiscountDTO.IdDTO id);

    void check(PCMerchMarketMerchantDiscountDTO.Check dto);
}
