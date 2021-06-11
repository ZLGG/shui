package com.gs.lshly.rpc.api.merchadmin.pc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchSkuGoodInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-08
*/
public interface IPCMerchAdminSkuGoodInfoRpc {

    PageData<PCMerchSkuGoodInfoVO.ListVO> pageData(PCMerchSkuGoodInfoQTO.QTO qto);

    void addSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto);

    void deleteSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto);
    void editSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto);

    PCMerchSkuGoodInfoVO.DetailVO detailSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto);

    List<PCMerchMarketPtSeckillVO.AllSkuVO> selectByGoodsId(String goodsId);
}