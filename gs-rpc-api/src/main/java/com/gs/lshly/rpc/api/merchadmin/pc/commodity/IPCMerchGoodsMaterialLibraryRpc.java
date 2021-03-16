package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsMaterialLibraryVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-12-10
*/
public interface IPCMerchGoodsMaterialLibraryRpc {

    /**
     * 根据条件查询素材库模板
     * @param qto
     * @return
     */
    List<PCMerchGoodsMaterialLibraryVO.DetailVO> detailGoodsMaterialLibrary(PCMerchGoodsMaterialLibraryQTO.QTO qto);

}