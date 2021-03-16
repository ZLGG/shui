package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSpuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSpuVO;

/**
*
* @author zdf
* @since 2020-12-01
*/
public interface IPCMerchMarketPtActivityGoodsSpuRpc {

    PageData<PCMerchMarketPtActivityGoodsSpuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSpuQTO.QTO qto);

    void addMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto);

    void deleteMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto);


    void editMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto);

    PCMerchMarketPtActivityGoodsSpuVO.DetailVO detailMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto);

}