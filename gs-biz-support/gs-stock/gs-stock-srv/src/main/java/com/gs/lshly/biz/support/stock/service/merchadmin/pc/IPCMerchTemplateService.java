package com.gs.lshly.biz.support.stock.service.merchadmin.pc;

import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;

public interface IPCMerchTemplateService {

    Boolean addTemplate(CommonTemplateDTO.AddETO eto);

    Boolean editTemplate(CommonTemplateDTO.UpdateETO eto);
}
