package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsSpecInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;

/**
*
* @author Starry
* @since 2020-10-09
*/
public interface IGoodsSpecInfoRpc {

    PageData<PCMerchGoodsSpecInfoVO.ListVO> pageData(PCMerchGoodsSpecInfoQTO.QTO qto);

    void addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto);

    void deleteGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto);
    void editGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto);

    PCMerchGoodsSpecInfoVO.DetailVO detailGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto);

}