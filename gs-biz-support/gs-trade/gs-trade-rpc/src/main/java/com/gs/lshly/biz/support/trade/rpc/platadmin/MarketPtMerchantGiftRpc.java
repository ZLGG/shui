package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantGiftService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantGiftRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtMerchantGiftRpc implements IMarketPtMerchantGiftRpc {
    @Autowired
    private IMarketPtMerchantGiftService iMarketPtMerchantGiftService;
    @Override
    public PageData<PCMerchMarketMerchantGiftVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        return iMarketPtMerchantGiftService.view(qto);
    }

    @Override
    public PCMerchMarketMerchantGiftVO.PlatformCutView get(PCMerchMarketMerchantGiftDTO.IdDTO id) {
        return iMarketPtMerchantGiftService.get(id);
    }

    @Override
    public void check(PCMerchMarketMerchantGiftDTO.Check dto) {
        iMarketPtMerchantGiftService.check(dto);
    }
}