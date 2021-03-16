package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 20:27 2020/10/19
 */
public interface IPCMerchAdminGoodsSpecDictionaryRpc {

    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsSpecDictionaryVO.DetailVO> listSpecDetail(PCMerchGoodsCategoryDTO.IdDTO dto);
}
