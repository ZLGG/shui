package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsAttributeInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;

/**
*
* @author Starry
* @since 2020-10-09
*/
public interface IGoodsAttributeInfoRpc {

    PageData<PCMerchGoodsAttributeInfoVO.ListVO> pageData(PCMerchGoodsAttributeInfoQTO.QTO qto);

    void addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto);

    void deleteGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto);
    void editGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto);

    PCMerchGoodsAttributeInfoVO.DetailVO detailGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto);

}