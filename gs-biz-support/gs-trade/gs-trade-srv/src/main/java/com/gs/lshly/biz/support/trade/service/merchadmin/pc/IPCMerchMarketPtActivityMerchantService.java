package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

import java.util.List;

public interface IPCMerchMarketPtActivityMerchantService {

    PageData<PCMerchMarketPtActivityMerchantVO.ListVO> pageData(PCMerchMarketPtActivityMerchantQTO.QTO qto);

    void addMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto);

    void deleteMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto);


    void editMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto);

    PCMerchMarketPtActivityMerchantVO.DetailVO detailMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto);

    List<PCMerchMarketPtActivityMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtActivityMerchantDTO.isRecordDTO dto);

    PageData<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity> queryMyList(PCMerchMarketPtActivityMerchantQTO.QTO qto);

    void merchantActivitySign(PCMerchMarketPtActivityGoodsSpuDTO.Sign dto);

    MarketPtActivityVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtActivityMerchantDTO.IdDTO dto);

    MarketPtActivityVO.MerchantViewDetails viewActivityListDetails(MarketPtActivityDTO.IdDTO dto);
}