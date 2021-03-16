package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;

public interface IMarketPtMerchantCardService {
    PageData<PCMerchMarketMerchantCardVO.DetailVO> view(PCMerchMarketMerchantCardQTO.QTO qto);

    PCMerchMarketMerchantCardVO.PlatformView get( PCMerchMarketMerchantCardDTO.IdDTO id);

    void check(PCMerchMarketMerchantCardDTO.Check dto);
}
