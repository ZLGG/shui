package com.gs.lshly.biz.support.foundation.service.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;

public interface IRemindMerchantService {

    PageData<RemindMerchantVO.ListVO> pageData(RemindMerchantQTO.QTO qto);

    void addRemindMerchant(RemindMerchantDTO.ETO eto);

    void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto);

    void editRemindMerchant(String id,RemindMerchantDTO.ETO eto);

    RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto);

}