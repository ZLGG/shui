package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;

public interface IPCMerchMarketMerchantDiscountService {

    PageData<PCMerchMarketMerchantDiscountVO.ViewVO> pageData(PCMerchMarketMerchantDiscountQTO.QTO qto);

    void addMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto);

    void deleteMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto);


    void editMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto);

    PCMerchMarketMerchantDiscountVO.View detailMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto);

    void commitMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto);

    void cancelMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto);
}