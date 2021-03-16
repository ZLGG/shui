package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;

public interface IMarketPtMerchantGroupbuyRpc {
    PageData<PCMerchMarketMerchantGroupbuyVO.PlatformView> view(PCMerchMarketMerchantGroupbuyQTO.QTO qto);

    PCMerchMarketMerchantGroupbuyVO.PlatformCutView get(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO id);

    void check(PCMerchMarketMerchantGroupbuyGoodsDTO.Check dto);

}
