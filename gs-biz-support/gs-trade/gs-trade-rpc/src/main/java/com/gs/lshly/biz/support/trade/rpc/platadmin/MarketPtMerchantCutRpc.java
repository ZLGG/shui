package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantCutService;
import com.gs.lshly.common.response.PageData;

import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCutRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtMerchantCutRpc implements IMarketPtMerchantCutRpc {
    @Autowired
    private IMarketPtMerchantCutService iMarketPtMerchantCutService;

    @Override
    public PageData<PCMerchMarketMerchantCutVO.PlatformView> view(PCMerchMarketMerchantCutQTO.QTO qto) {
        return iMarketPtMerchantCutService.view(qto);
    }

    @Override
    public PCMerchMarketMerchantCutVO.PlatformCutView get(PCMerchMarketMerchantCutDTO.IdDTO id) {
        return iMarketPtMerchantCutService.get(id);
    }

    @Override
    public void check(PCMerchMarketMerchantCutDTO.Check dto) {
        iMarketPtMerchantCutService.check(dto);
    }
}
