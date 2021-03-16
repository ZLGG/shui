package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;

public interface IPCMerchMarketMerchantCardGoodsService {

    PageData<PCMerchMarketMerchantCardGoodsVO.ListVO> pageData(PCMerchMarketMerchantCardGoodsQTO.QTO qto);

    void addMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto);

    void deleteMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto);


    void editMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto);

    PCMerchMarketMerchantCardGoodsVO.DetailVO detailMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto);

}