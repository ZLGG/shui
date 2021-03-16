package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsVO;

public interface IPCMerchMarketMerchantGiftGoodsService {

    PageData<PCMerchMarketMerchantGiftGoodsVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsQTO.QTO qto);

    void addMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto);

    void deleteMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto);


    void editMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto);

    PCMerchMarketMerchantGiftGoodsVO.DetailVO detailMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto);

}