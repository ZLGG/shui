package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantPermissionDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantPermissionDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantPermissionDictVO;

public interface IMerchantPermissionDictService {

    PageData<MerchantPermissionDictVO.ListVO> pageData(MerchantPermissionDictQTO.QTO qto);

    void addMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto);

    void deleteMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto);
    void editMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto);

    MerchantPermissionDictVO.DetailVO detailMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto);

}