package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;

/**
*
* @author zdf
* @since 2020-12-09
*/
public interface IPCMerchMarketMerchantGiftRpc {

    PageData<PCMerchMarketMerchantGiftVO.ViewVO> pageData(PCMerchMarketMerchantGiftQTO.QTO qto);

    void addMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto);

    void deleteMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto);


    void editMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto);

    PCMerchMarketMerchantGiftVO.View detailMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto);

    void commitMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto);

    void cancelMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto);
}