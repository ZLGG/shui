package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;

public interface IMarketPtActivityCheckRpc {

    PageData<PCMerchMarketPtActivityMerchantVO.platformCheck> noChcekList(MarketPtActivityQTO.QTO qto);

    PCMerchMarketPtActivityMerchantVO.platformCheckView checkView(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto);

    void checkSuccess(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto);

    void CheckFail(PCMerchMarketPtActivityMerchantDTO.idRecordRejectionDTO dto);
}
