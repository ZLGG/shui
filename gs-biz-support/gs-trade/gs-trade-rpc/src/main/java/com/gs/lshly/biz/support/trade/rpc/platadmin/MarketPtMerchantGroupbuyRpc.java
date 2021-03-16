package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantGroupbuyService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantGroupbuyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtMerchantGroupbuyRpc implements IMarketPtMerchantGroupbuyRpc {
    @Autowired
    private IMarketPtMerchantGroupbuyService iMarketPtMerchantGroupbuyService;

    @Override
    public PageData<PCMerchMarketMerchantGroupbuyVO.PlatformView> view(PCMerchMarketMerchantGroupbuyQTO.QTO qto) {
        return iMarketPtMerchantGroupbuyService.view(qto);
    }

    @Override
    public PCMerchMarketMerchantGroupbuyVO.PlatformCutView get(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO id) {
       return iMarketPtMerchantGroupbuyService.get(id);
    }

    @Override
    public void check(PCMerchMarketMerchantGroupbuyGoodsDTO.Check dto) {
       iMarketPtMerchantGroupbuyService.check(dto);
    }
}