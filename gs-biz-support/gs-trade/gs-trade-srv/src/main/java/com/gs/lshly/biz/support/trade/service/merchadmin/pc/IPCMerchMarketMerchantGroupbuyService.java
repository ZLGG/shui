package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;

public interface IPCMerchMarketMerchantGroupbuyService {

    PageData<PCMerchMarketMerchantGroupbuyVO.ViewVO> pageData(PCMerchMarketMerchantGroupbuyQTO.QTO qto);

    void addMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO dto);

    void deleteMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto);


    void editMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto);

    PCMerchMarketMerchantGroupbuyVO.View detailMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto);

    void commitMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto);

    void cancelMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto);
}