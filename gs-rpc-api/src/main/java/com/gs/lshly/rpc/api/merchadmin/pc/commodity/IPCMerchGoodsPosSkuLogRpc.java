package com.gs.lshly.rpc.api.merchadmin.pc.commodity;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-15
*/
public interface IPCMerchGoodsPosSkuLogRpc {


    /**
     * 批量添加sku商品pos记录
     * @param etoList
     */
    void addGoodsPosSkuLog(List<PCMerchGoodsPosSkuLogDTO.ETO> etoList);

}