package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantApplyVO;

import java.util.List;

public interface IMerchantApplyService {

    PageData<MerchantApplyVO.ListVO> pageData(MerchantApplyQTO.QTO qto);

    PageData<MerchantApplyVO.ListVO> pageSearch(MerchantApplyQTO.SearchQTO qto);

    void deleteBatchMerchantApply(MerchantApplyDTO.IdListDTO dto);

    void apply(MerchantApplyDTO.ApplyDTO dto);

    /**
     * 审核入驻信息资料
     * @param dto
     */
    void checkEditApply(MerchantApplyDTO.CheckApplyDTO dto);


    void openShop(MerchantApplyDTO.IdDTO eto);

    MerchantApplyVO.BrandVO handBrandQuery(MerchantApplyDTO.IdDTO dto);

    void handBrandSubmit(MerchantApplyDTO.HandBrandDTO dto);

    MerchantApplyVO.DetailVO detailMerchantApply(MerchantApplyDTO.IdDTO dto);


}