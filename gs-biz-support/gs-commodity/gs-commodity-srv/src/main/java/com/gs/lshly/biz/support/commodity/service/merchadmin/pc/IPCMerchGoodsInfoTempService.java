package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;

/**
 * 商品临时表 service
 */
public interface IPCMerchGoodsInfoTempService {

    /**
     * 修改商品信息
     * @param eto
     */
    void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto);
}
