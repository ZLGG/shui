package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountGoodsVO;

/**
*
* @author zdf
* @since 2020-12-09
*/
public interface IPCMerchMarketMerchantDiscountGoodsRpc {

    PageData<PCMerchMarketMerchantDiscountGoodsVO.ListVO> pageData(PCMerchMarketMerchantDiscountGoodsQTO.QTO qto);

    void addMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto);

    void deleteMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto);


    void editMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto);

    PCMerchMarketMerchantDiscountGoodsVO.DetailVO detailMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto);

}