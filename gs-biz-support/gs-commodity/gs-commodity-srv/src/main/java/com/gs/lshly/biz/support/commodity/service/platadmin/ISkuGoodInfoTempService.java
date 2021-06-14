package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.SkuGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;

import java.util.List;

public interface ISkuGoodInfoTempService {

    /**
     * 根据商品id查询sku列表
     * @param dto
     * @return
     */
    List<SkuGoodsInfoVO.DetailVO> listSku(SkuGoodsInfoDTO.GoodsIdDTO dto);

    List<PCMerchSkuGoodInfoVO.DetailVO> getByGoodsId(PCMerchSkuGoodInfoDTO.GoodIdDTO goodId);
}
