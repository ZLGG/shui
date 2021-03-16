package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;

/**
*
* @author zdf
* @since 2020-12-09
*/
public interface IPCMerchMarketMerchantDiscountRpc {

    PageData<PCMerchMarketMerchantDiscountVO.ViewVO> pageData(PCMerchMarketMerchantDiscountQTO.QTO qto);

    void addMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto);

    void deleteMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto);


    void editMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto);

    PCMerchMarketMerchantDiscountVO.View detailMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto);

    void commitMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto);

    void cancelMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto);
}