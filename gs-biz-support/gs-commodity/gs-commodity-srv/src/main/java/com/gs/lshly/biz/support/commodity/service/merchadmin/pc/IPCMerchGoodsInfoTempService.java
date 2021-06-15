package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;

/**
 * 商品临时表 service
 */
public interface IPCMerchGoodsInfoTempService {

    /**
     * 修改商品信息
     * @param eto
     */
    void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto);

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
     * 新增商品
     * @param eto
     * @return
     */
    PCMerchGoodsInfoVO.GoodsIdVO addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto);

    int countGoodsNo(PCMerchGoodsInfoDTO.GoodNoDTO dto);

    Boolean deleteBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO);
}
