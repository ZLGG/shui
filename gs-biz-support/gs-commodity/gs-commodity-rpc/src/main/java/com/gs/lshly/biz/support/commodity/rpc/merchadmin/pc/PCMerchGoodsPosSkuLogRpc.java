package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosSkuLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosSkuLogVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosSkuLogRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosSkuLogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-15
*/
@DubboService
public class PCMerchGoodsPosSkuLogRpc implements IPCMerchGoodsPosSkuLogRpc{
    @Autowired
    private IPCMerchGoodsPosSkuLogService  pCMerchGoodsPosSkuLogService;


    @Override
    public void addGoodsPosSkuLog(List<PCMerchGoodsPosSkuLogDTO.ETO> etoList) {
        pCMerchGoodsPosSkuLogService.addGoodsPosSkuLog(etoList);
    }

}