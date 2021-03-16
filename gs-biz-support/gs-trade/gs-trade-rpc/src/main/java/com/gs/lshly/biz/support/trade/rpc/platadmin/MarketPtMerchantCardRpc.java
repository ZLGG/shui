package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantCardService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCardRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtMerchantCardRpc implements IMarketPtMerchantCardRpc {
    @Autowired
    private IMarketPtMerchantCardService iMarketPtMerchantCardService;
    @Override
    public PageData<PCMerchMarketMerchantCardVO.DetailVO> view(PCMerchMarketMerchantCardQTO.QTO qto) {
        return iMarketPtMerchantCardService.view(qto);
    }

    @Override
    public PCMerchMarketMerchantCardVO.PlatformView get( PCMerchMarketMerchantCardDTO.IdDTO id) {
        return iMarketPtMerchantCardService.get(id);
    }

    @Override
    public void check(PCMerchMarketMerchantCardDTO.Check dto) {
        iMarketPtMerchantCardService.check(dto);
    }
}
