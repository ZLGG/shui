package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;

import java.util.List;

public interface IGoodsExtendParamsTempService {

    String addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto);

    /**
     * 根据商品id获取商品拓展参数
     * @param dto
     * @return
     */
    List<PCMerchGoodsExtendParamsVO.ListVO> extendListVO(PCMerchGoodsExtendParamsDTO.GoodIdDTO dto);
}
