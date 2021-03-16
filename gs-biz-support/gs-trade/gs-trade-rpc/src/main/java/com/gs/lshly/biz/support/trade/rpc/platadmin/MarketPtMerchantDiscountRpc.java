package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantDiscountService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantDiscountRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtMerchantDiscountRpc implements IMarketPtMerchantDiscountRpc {
    @Autowired
    private IMarketPtMerchantDiscountService iMarketPtMerchantCutService;

    @Override
    public PageData<PCMerchMarketMerchantDiscountVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        return iMarketPtMerchantCutService.view(qto);
    }

    @Override
    public PCMerchMarketMerchantDiscountVO.PlatformCutView get( PCMerchMarketMerchantDiscountDTO.IdDTO id) {
        return iMarketPtMerchantCutService.get(id);
    }

    @Override
    public void check(PCMerchMarketMerchantDiscountDTO.Check dto) {
        iMarketPtMerchantCutService.check(dto);
    }
}
