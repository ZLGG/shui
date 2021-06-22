package com.gs.lshly.biz.support.stock.service.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;

public interface IPCMerchTemplateService {

    Boolean addTemplate(CommonTemplateDTO.AddETO eto);

    Boolean editTemplate(CommonTemplateDTO.UpdateETO eto);

    TemplateVO.DetailVO detail(String id);

    Boolean delete(String id);

    PageData<TemplateVO.ListVO> list(TemplateQTO.QTO qto);
}
