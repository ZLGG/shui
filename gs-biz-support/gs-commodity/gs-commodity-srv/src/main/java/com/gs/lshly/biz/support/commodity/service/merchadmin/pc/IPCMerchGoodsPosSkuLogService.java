package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;

import java.util.List;

public interface IPCMerchGoodsPosSkuLogService {


    /**
     * 批量添加sku商品pos记录
     * @param etoList
     */
    void addGoodsPosSkuLog(List<PCMerchGoodsPosSkuLogDTO.ETO> etoList);


}