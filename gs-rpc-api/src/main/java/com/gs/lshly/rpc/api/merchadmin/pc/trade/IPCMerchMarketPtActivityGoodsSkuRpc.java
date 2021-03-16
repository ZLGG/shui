package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSkuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSkuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSkuVO;

/**
*
* @author zdf
* @since 2020-12-02
*/
public interface IPCMerchMarketPtActivityGoodsSkuRpc {

    PageData<PCMerchMarketPtActivityGoodsSkuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSkuQTO.QTO qto);

    void addMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto);

    void deleteMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto);


    void editMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto);

    PCMerchMarketPtActivityGoodsSkuVO.DetailVO detailMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto);

}