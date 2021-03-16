package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsVO;

/**
*
* @author zdf
* @since 2020-12-09
*/
public interface IPCMerchMarketMerchantGiftGoodsRpc {

    PageData<PCMerchMarketMerchantGiftGoodsVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsQTO.QTO qto);

    void addMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto);

    void deleteMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto);


    void editMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto);

    PCMerchMarketMerchantGiftGoodsVO.DetailVO detailMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto);

}