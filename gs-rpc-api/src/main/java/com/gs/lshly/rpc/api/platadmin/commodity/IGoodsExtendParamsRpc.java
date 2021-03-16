package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsExtendParamsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;

/**
*
* @author Starry
* @since 2020-10-09
*/
public interface IGoodsExtendParamsRpc {

    PageData<PCMerchGoodsExtendParamsVO.ListVO> pageData(PCMerchGoodsExtendParamsQTO.QTO qto);

    void addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto);

    void deleteGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto);
    void editGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto);

    PCMerchGoodsExtendParamsVO.DetailVO detailGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto);

}