package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsMaterialLibraryVO;

import java.util.List;


/**
 * @author Starry
 */
public interface IPCMerchGoodsMaterialLibraryService {

    /**
     * 根据条件查询素材库模板
     * @param qto
     * @return
     */
    List<PCMerchGoodsMaterialLibraryVO.DetailVO> detailGoodsMaterialLibrary(PCMerchGoodsMaterialLibraryQTO.QTO qto);

}