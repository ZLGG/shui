package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantPermissionDictDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantPermissionDictQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-26
*/
public interface IPCMerchMerchantPermissionDictRpc {

    List<PCMerchMerchantPermissionDictVO.ListVO> list(PCMerchMerchantPermissionDictQTO.QTO qto);

    void addMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto);

    void deleteMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.IdDTO dto);

    void editMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto);

}