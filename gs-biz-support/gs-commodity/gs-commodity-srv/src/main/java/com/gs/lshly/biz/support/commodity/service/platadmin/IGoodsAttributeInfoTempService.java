package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;

public interface IGoodsAttributeInfoTempService {

    /**
     * 添加商品拓展属性信息
     * @param dto
     * @return
     */
    String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO dto);
}
