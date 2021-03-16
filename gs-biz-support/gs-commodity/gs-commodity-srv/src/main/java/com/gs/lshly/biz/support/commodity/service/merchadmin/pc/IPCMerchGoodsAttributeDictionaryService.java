package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 14:31 2020/10/20
 */
public interface IPCMerchGoodsAttributeDictionaryService {
    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(PCMerchGoodsCategoryDTO.IdDTO dto);
}
