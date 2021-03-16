package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityCheckService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtActivityCheckRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MarketPtActivityCheckRpc implements IMarketPtActivityCheckRpc {
    @Autowired
    private IMarketPtActivityCheckService iMarketPtActivityCheckService;
    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.platformCheck> noChcekList(MarketPtActivityQTO.QTO qto) {
        return iMarketPtActivityCheckService.noChcekList(qto);
    }

    @Override
    public PCMerchMarketPtActivityMerchantVO.platformCheckView checkView(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        return iMarketPtActivityCheckService.checkView(dto);
    }

    @Override
    public void checkSuccess(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        iMarketPtActivityCheckService.checkSuccess(dto);
    }

    @Override
    public void CheckFail(PCMerchMarketPtActivityMerchantDTO.idRecordRejectionDTO dto) {
        iMarketPtActivityCheckService.CheckFail(dto);
    }
}
