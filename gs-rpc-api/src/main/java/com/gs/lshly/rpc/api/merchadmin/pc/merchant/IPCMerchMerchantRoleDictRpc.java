package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantRoleDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantRoleDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantRoleDictVO;

/**
*
* @author xxfc
* @since 2020-10-08
*/
public interface IPCMerchMerchantRoleDictRpc {

    PageData<MerchantRoleDictVO.ListVO> pageData(MerchantRoleDictQTO.QTO qto);

    MerchantRoleDictVO.DetailVO detail(MerchantRoleDictDTO.IdDTO dto);

    void addMerchantRoleDict(MerchantRoleDictDTO.ETO eto);

    void deleteMerchantRoleDict(MerchantRoleDictDTO.IdDTO dto);

    void editMerchantRoleDict(MerchantRoleDictDTO.ETO eto);
}