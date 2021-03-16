package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyGoodsVO;

/**
*
* @author zdf
* @since 2020-12-10
*/
public interface IPCMerchMarketMerchantGroupbuyGoodsRpc {

    PageData<PCMerchMarketMerchantGroupbuyGoodsVO.ListVO> pageData(PCMerchMarketMerchantGroupbuyGoodsQTO.QTO qto);

    void addMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto);

    void deleteMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto);


    void editMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto);

    PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO detailMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto);

}