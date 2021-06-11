package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;

import java.util.List;

public interface IGoodsSpecInfoTempService {

    String addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto);

    /**
     * 根据商品id获取商品拓展规格
     * @param dto
     * @return
     */
    List<PCMerchGoodsSpecInfoVO.ListVO> specInfoListVO (PCMerchGoodsSpecInfoDTO.GoodIdDTO dto);
}
