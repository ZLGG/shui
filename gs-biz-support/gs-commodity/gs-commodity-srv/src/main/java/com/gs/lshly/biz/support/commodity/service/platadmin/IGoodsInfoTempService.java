package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

public interface IGoodsInfoTempService {
    /**
     * 审核-获取商品详情
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto);

    /**
     * 判断商品是新增还是更新
     * @param goodId
     * @return
     */
    Boolean isUpdateGoodInfo(String goodId);

    /**
     * 获取审核商品详情
     * @param dto
     * @return
     */
    GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto);
}
