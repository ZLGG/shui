package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 14:44 2020/10/20
 */
public interface IPCMerchAdminGoodsAttributeDictionaryRpc {
    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(PCMerchGoodsCategoryDTO.IdDTO dto);
}