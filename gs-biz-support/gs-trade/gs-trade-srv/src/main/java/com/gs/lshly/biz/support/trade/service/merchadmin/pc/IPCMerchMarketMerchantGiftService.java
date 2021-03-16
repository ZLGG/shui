package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;

public interface IPCMerchMarketMerchantGiftService {

    PageData<PCMerchMarketMerchantGiftVO.ViewVO> pageData(PCMerchMarketMerchantGiftQTO.QTO qto);

    void addMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto);

    void deleteMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto);


    void editMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto);

    PCMerchMarketMerchantGiftVO.View detailMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto);

    void commitMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto);

    void cancelMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto);
}