package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;

import java.util.List;

public interface IPCMerchSkuGoodInfoTempService {

    List<PCMerchSkuGoodInfoVO.DetailVO> getByGoodsId(PCMerchSkuGoodInfoDTO.GoodIdDTO goodId);
}
