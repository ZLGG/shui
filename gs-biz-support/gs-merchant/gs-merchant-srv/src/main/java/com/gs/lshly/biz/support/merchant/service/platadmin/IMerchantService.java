package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantVO;

public interface IMerchantService {

    PageData<MerchantVO.ListVO> pageData(MerchantQTO.QTO qto);

    void addMerchant(MerchantDTO.ETO eto);

    void deleteMerchant(MerchantDTO.IdDTO dto);
    void editMerchant(MerchantDTO.ETO eto);

    MerchantVO.DetailVO detailMerchant(MerchantDTO.IdDTO dto);

}