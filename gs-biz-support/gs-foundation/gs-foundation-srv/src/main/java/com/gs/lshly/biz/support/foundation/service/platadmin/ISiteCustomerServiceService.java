package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteCustomerServiceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteCustomerServiceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteCustomerServiceVO;

import java.util.List;

public interface ISiteCustomerServiceService {

    List<SiteCustomerServiceVO.ListVO> list(SiteCustomerServiceQTO.QTO qto);

    void addSiteCustomerService(SiteCustomerServiceDTO.ETO eto);

    List<SiteCustomerServiceVO.PhoneVO> listPhone(SiteCustomerServiceQTO.QTO qto);

    void addSiteCustomerServicePhone(SiteCustomerServiceDTO.ETOPhone eto);
}