package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsGiveDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsGiveQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsGiveVO;

/**
*
* @author zdf
* @since 2020-12-09
*/
public interface IPCMerchMarketMerchantGiftGoodsGiveRpc {

    PageData<PCMerchMarketMerchantGiftGoodsGiveVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsGiveQTO.QTO qto);

    void addMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto);

    void deleteMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto);


    void editMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto);

    PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO detailMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto);

}