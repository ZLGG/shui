package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantPermissionDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantPermissionDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantPermissionDictVO;

/**
*
* @author xxfc
* @since 2020-10-08
*/
public interface IMerchantPermissionDictRpc {

    PageData<MerchantPermissionDictVO.ListVO> pageData(MerchantPermissionDictQTO.QTO qto);

    void addMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto);

    void deleteMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto);

    void editMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto);

    MerchantPermissionDictVO.DetailVO detailMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto);

}