package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsExtendParamsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;

import java.util.List;

public interface IGoodsExtendParamsService {

    PageData<PCMerchGoodsExtendParamsVO.ListVO> pageData(PCMerchGoodsExtendParamsQTO.QTO qto);

    String addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto);

    void deleteGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto);
    void editGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto);

    PCMerchGoodsExtendParamsVO.DetailVO detailGoodsExtendParams(PCMerchGoodsExtendParamsDTO.IdDTO dto);

    /**
     * 根据商品id获取商品拓展参数
     * @param dto
     * @return
     */
    List<PCMerchGoodsExtendParamsVO.ListVO> extendListVO(PCMerchGoodsExtendParamsDTO.GoodIdDTO dto);
}