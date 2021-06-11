package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;

import java.util.List;

public interface IGoodsAttributeInfoTempService {

    /**
     * 添加商品拓展属性信息
     * @param dto
     * @return
     */
    String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO dto);

    /**
     * 根据商品id获取商品拓展属性列表
     * @param dto
     * @return
     */
    List<PCMerchGoodsAttributeInfoVO.ListVO> getListVO(PCMerchGoodsAttributeInfoDTO.GoodIdDTO dto);
}
