package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantPermissionDictDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantPermissionDictQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;

import java.util.List;

public interface IPCMerchMerchantPermissionDictService {

    List<PCMerchMerchantPermissionDictVO.ListVO> list(PCMerchMerchantPermissionDictQTO.QTO qto);

    void addMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto);

    void deleteMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.IdDTO dto);


    void editMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto);

}