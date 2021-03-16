package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;

public interface IPCMerchMarketMerchantCardService {

    PageData<PCMerchMarketMerchantCardVO.ListVO> pageData(PCMerchMarketMerchantCardQTO.QTO qto);

    void addMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto);

    void deleteMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto);


    void editMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto);

    PCMerchMarketMerchantCardVO.DetailVO detailMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto);

    void cancelMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto);

    void commitMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto);

    PCMerchMarketMerchantCardVO.View viewMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto);
}