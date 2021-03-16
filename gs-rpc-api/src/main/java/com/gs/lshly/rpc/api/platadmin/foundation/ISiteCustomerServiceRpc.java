package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteCustomerServiceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteCustomerServiceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteCustomerServiceVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISiteCustomerServiceRpc {

    List<SiteCustomerServiceVO.ListVO> list (SiteCustomerServiceQTO.QTO qto);

    void addSiteCustomerServic(SiteCustomerServiceDTO.ETO eto);

    List<SiteCustomerServiceVO.PhoneVO> listPhone(SiteCustomerServiceQTO.QTO qto);

    void addSiteCustomerServicPhone(SiteCustomerServiceDTO.ETOPhone  dto);
}