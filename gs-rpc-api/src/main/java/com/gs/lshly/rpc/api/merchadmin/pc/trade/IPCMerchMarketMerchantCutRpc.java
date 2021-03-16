package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;

/**
*
* @author zdf
* @since 2020-12-08
*/
public interface IPCMerchMarketMerchantCutRpc {

    PageData<PCMerchMarketMerchantCutVO.ViewVO> pageData(PCMerchMarketMerchantCutQTO.QTO qto);

    void addMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto);

    void deleteMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto);


    void editMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto);

    PCMerchMarketMerchantCutVO.View detailMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto);

    void commitMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto);

    void cancelMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto);
}