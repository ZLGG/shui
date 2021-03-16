package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsMaterialLibraryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsMaterialLibraryRpc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsMaterialLibraryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-10
*/
@DubboService
public class PCMerchGoodsMaterialLibraryRpc implements IPCMerchGoodsMaterialLibraryRpc{
    @Autowired
    private IPCMerchGoodsMaterialLibraryService  pCMerchGoodsMaterialLibraryService;


    @Override
    public List<PCMerchGoodsMaterialLibraryVO.DetailVO> detailGoodsMaterialLibrary(PCMerchGoodsMaterialLibraryQTO.QTO qto) {
        return pCMerchGoodsMaterialLibraryService.detailGoodsMaterialLibrary(qto);
    }
}