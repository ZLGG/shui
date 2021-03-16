package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantRoleDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantRoleDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantRoleDictVO;

public interface IPCMerchantRoleDictService {

    PageData<MerchantRoleDictVO.ListVO> pageData(MerchantRoleDictQTO.QTO qto);

    MerchantRoleDictVO.DetailVO detail(MerchantRoleDictDTO.IdDTO dto);

    void addMerchantRoleDict(MerchantRoleDictDTO.ETO eto);

    void deleteMerchantRoleDict(MerchantRoleDictDTO.IdDTO dto);

    void editMerchantRoleDict(MerchantRoleDictDTO.ETO eto);

}