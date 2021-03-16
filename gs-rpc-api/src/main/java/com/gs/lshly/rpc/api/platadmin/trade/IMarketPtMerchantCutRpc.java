package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;

public interface IMarketPtMerchantCutRpc {
    PageData<PCMerchMarketMerchantCutVO.PlatformView> view(PCMerchMarketMerchantCutQTO.QTO qto);

    PCMerchMarketMerchantCutVO.PlatformCutView get(PCMerchMarketMerchantCutDTO.IdDTO id);

    void check(PCMerchMarketMerchantCutDTO.Check dto);
}
