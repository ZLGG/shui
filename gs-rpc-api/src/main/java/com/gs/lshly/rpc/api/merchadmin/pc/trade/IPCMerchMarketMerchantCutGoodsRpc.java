package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutGoodsVO;

/**
*
* @author zdf
* @since 2020-12-08
*/
public interface IPCMerchMarketMerchantCutGoodsRpc {

    PageData<PCMerchMarketMerchantCutGoodsVO.ListVO> pageData(PCMerchMarketMerchantCutGoodsQTO.QTO qto);

    void addMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto);

    void deleteMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto);


    void editMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto);

    PCMerchMarketMerchantCutGoodsVO.DetailVO detailMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto);

}