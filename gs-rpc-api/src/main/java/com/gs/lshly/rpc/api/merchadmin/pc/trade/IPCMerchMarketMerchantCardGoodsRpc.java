package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;

/**
*
* @author zdf
* @since 2020-12-04
*/
public interface IPCMerchMarketMerchantCardGoodsRpc {

    PageData<PCMerchMarketMerchantCardGoodsVO.ListVO> pageData(PCMerchMarketMerchantCardGoodsQTO.QTO qto);

    void addMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto);

    void deleteMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto);


    void editMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto);

    PCMerchMarketMerchantCardGoodsVO.DetailVO detailMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto);

}