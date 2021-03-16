package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 20:29 2020/10/19
 */
public interface IPCMerchGoodsSpecDictionaryService {

    /**
     * 获取与类目关联的属性以及属性详情信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsSpecDictionaryVO.DetailVO> listSpecDetail(PCMerchGoodsCategoryDTO.IdDTO dto);

    //----------内部服务------------

    /**
     * 修改拓展规格信息
     * @param etoList
     * @param goodsId
     */
    void updateSpecInfo(List<PCMerchGoodsSpecInfoDTO.ETO> etoList,String goodsId);

}
