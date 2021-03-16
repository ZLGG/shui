package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;

public interface IMarketPtMerchantDiscountRpc {

    PageData<PCMerchMarketMerchantDiscountVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto);

    PCMerchMarketMerchantDiscountVO.PlatformCutView get( PCMerchMarketMerchantDiscountDTO.IdDTO id);

    void check(PCMerchMarketMerchantDiscountDTO.Check dto);
}
