package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
public interface IMarketPtMerchantCutService {

    PageData<PCMerchMarketMerchantCutVO.PlatformView> view(PCMerchMarketMerchantCutQTO.QTO qto);

    PCMerchMarketMerchantCutVO.PlatformCutView get(PCMerchMarketMerchantCutDTO.IdDTO id);

    void check(PCMerchMarketMerchantCutDTO.Check dto);
}
